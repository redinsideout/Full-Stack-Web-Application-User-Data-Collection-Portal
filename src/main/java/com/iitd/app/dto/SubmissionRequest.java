package com.iitd.app.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class SubmissionRequest {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Email is required")
    @Email(message = "Must be a valid email")
    private String email;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10,15}$", message = "Mobile number must contain only digits (10-15 digits)")
    private String mobileNumber;
}
