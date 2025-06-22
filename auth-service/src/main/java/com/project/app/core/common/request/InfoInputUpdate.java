package com.project.app.core.common.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InfoInputUpdate <T,K>{
  @Valid
  @NotNull(message = "ID must not be null")
  private T id;
  @NotNull(message = "Input must not be null")
  private K input;
}