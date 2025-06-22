package com.project.app.exception;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class ValidationException extends RuntimeException {
  private Map<String, String> validationErrors;

}
