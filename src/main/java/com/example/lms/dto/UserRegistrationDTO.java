package com.example.lms.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRegistrationDTO {
    @NotEmpty(message = "Full name is required")
    private String fullName;

    @NotEmpty(message = "Username is required")
    private String username;

    @NotEmpty(message = "Password is required")
    private String password;

    @NotEmpty(message = "Confirm Password is required")
    private String confirmPassword;
}
