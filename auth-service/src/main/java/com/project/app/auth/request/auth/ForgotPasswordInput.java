package com.project.app.auth.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for password reset request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswordInput {

    /**
     * User's email for password reset.
     */
    @NotBlank(message = "Email must not be blank.")
    @Email(message = "Invalid email address.")
    private String email;
}
