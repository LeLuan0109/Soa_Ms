package com.project.app.business.domain.enums;

public enum StatusType {
  PENDING_APPROVAL(0, "Chờ phê duyệt"),
  STAFF_APPROVING(1, "Cấp chuyên viên duyệt"),
  MANAGER_APPROVING(2, "Cấp phê duyệt duyệt"),
  STAFF_REJECTED(3, "Cấp chuyên viên từ chối"),
  MANAGER_REJECTED(4, "Cấp phê duyệt từ chối");

  private final int code;
  private final String description;

  StatusType(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  public static StatusType fromCode(int code) {
    for (StatusType status : StatusType.values()) {
      if (status.getCode() == code) {
        return status;
      }
    }
    throw new IllegalArgumentException("Invalid status code: " + code);
  }
}
