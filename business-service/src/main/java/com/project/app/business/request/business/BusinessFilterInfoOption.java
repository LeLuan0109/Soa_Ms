package com.project.app.business.request.business;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusinessFilterInfoOption {

  @NotNull(message = "Business ID cannot be null")  // Ensure businessId is not null
  private Integer businessId;

  @NotNull(message = "Type option cannot be null")  // Ensure typeOption is not null
  @Size(min = 1, max = 50, message = "Type option must be between 1 and 50 characters")  // Ensure typeOption has a valid length
  private String typeOption;

  @Min(value = 1, message = "Top value must be greater than or equal to 1")  // Ensure top is at least 1
  private int top;
}
