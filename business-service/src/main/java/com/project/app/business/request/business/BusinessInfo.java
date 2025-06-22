package com.project.app.business.request.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessInfo {

  @NotBlank(message = "infoBusiness must not be blank")
  @Size(max = 255, message = "infoBusiness must not exceed 255 characters")
  private String infoBusiness;

  @NotBlank(message = "taxCode is required")
  @Pattern(regexp = "^\\d{1,20}$", message = "taxCode must contain only digits and be up to 20 characters")
  private String taxCode;

  @NotBlank(message = "stockCode is required")
  @Pattern(regexp = "^\\d{1,20}$", message = "stockCode must contain only digits and be up to 20 characters")
  private String stockCode;

  @NotBlank(message = "idCard is required")
  @Pattern(regexp = "^\\d{1,20}$", message = "idCard must contain only digits and be up to 20 characters")
  private String idCard;

  @Size(max = 255, message = "forSearching must not exceed 255 characters")
  private String forSearching;
}
