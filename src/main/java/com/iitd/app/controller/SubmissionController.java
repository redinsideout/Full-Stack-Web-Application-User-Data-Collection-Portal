package com.iitd.app.controller;

import com.iitd.app.entity.Submission;
import com.iitd.app.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @PostMapping("/submissions")
    public ResponseEntity<?> submitForm(
            @RequestParam("fullName") String fullName,
            @RequestParam("email") String email,
            @RequestParam("mobileNumber") String mobileNumber,
            @RequestParam("pdfFile") MultipartFile pdfFile,
            Authentication authentication) {

        try {
            if (fullName == null || fullName.isBlank()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Full name is required"));
            }
            if (email == null || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "Valid email is required"));
            }
            if (mobileNumber == null || !mobileNumber.matches("^[0-9]{10,15}$")) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "Mobile number must be of 10 digits"));
            }
            if (pdfFile == null || pdfFile.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("success", false, "message", "PDF file is required"));
            }
            if (pdfFile.getSize() > 5 * 1024 * 1024) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "PDF file must be less than 5MB"));
            }

            String userEmail = authentication.getName();
            Submission submission = submissionService.saveSubmission(
                    userEmail, fullName, email, mobileNumber, pdfFile);

            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Form submitted successfully!",
                    "submissionId", submission.getId()));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("success", false, "message", e.getMessage()));
        } catch (IOException e) {
            return ResponseEntity.status(500)
                    .body(Map.of("success", false, "message", "File upload failed: " + e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500)
                    .body(Map.of("success", false, "message", "An error occurred: " + e.getMessage()));
        }
    }

    @GetMapping("/user/download-pdf")
    public ResponseEntity<byte[]> downloadPdf(Authentication authentication) {
        try {
            String userEmail = authentication.getName();
            byte[] pdfBytes = submissionService.generateUserPdf(userEmail);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.attachment()
                    .filename("my-details.pdf")
                    .build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);

        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
