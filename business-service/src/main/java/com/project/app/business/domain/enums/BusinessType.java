package com.project.app.business.domain.enums;

public enum BusinessType {
  TAX_STATUS("taxStatus"),
  BUSINESS_STATUS("businessStatus"),
  CUSTOM_DEPT("customDept"),
  BRANCH_DEPT("branchDept"),
  POST("post");

  private final String value;

  BusinessType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static BusinessType fromValue(String value) {
    for (BusinessType type : values()) {
      if (type.value.equalsIgnoreCase(value)) {
        return type;
      }
    }
    throw new IllegalArgumentException("Unknown BusinessType: " + value);
  }
}
