package com.project.app.auth.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassWordInput {
    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters long")
    private String passwordNew;

    @NotBlank(message = "Confirm password is required")
    @Size(min = 8, message = "Confirm password must be at least 8 characters long")
    private String confirmPassword;
}
