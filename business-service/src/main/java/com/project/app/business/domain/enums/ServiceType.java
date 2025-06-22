package com.project.app.business.domain.enums;

public enum ServiceType {
  BRANCH_TAX_DEBT("tax-debt"),
  CUSTOMS_DEBT("maritime-tax-debt"),
  NEWS("website"),
  FACEBOOK("social");

  private final String value;

  ServiceType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static ServiceType fromValue(String input) {
    try {
      return ServiceType.valueOf(input.toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Invalid ServiceType: " + input);
    }
  }
}
