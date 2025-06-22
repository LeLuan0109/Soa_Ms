package com.project.app.auth.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordInput extends PassWordInput {
  @NotBlank(message = "Current password is required")
  @Size(min = 8, message = "Current password must be at least 8 characters long")
  private String password;
}
