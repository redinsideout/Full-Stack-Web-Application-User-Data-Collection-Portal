package com.iitd.app.controller;

import com.iitd.app.dto.LoginRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest,
                                    HttpSession session) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
                )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "email", authentication.getName()
            ));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of(
                "success", false,
                "message", "Invalid email or password"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of(
                "success", false,
                "message", "An error occurred. Please try again."
            ));
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> status(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return ResponseEntity.ok(Map.of(
                "authenticated", true,
                "email", authentication.getName()
            ));
        }
        return ResponseEntity.ok(Map.of("authenticated", false));
    }
}
