package com.project.app.core.enums;

public enum ProcessingStatus {
  PENDING(0, "Chưa xử lý"),
  IN_PROGRESS(1, "Đang xử lý"),
  FAILED(2, "Xử lý thất bại"),
  SUCCESS(3, "Xử lý thành công");

  private final int code;
  private final String description;

  // Constructor
  ProcessingStatus(int code, String description) {
    this.code = code;
    this.description = description;
  }

  // Getter cho code
  public int getCode() {
    return code;
  }

  // Getter cho description
  public String getDescription() {
    return description;
  }

  // Phương thức lấy enum từ code
  public static ProcessingStatus fromCode(int code) {
    for (ProcessingStatus status : values()) {
      if (status.getCode() == code) {
        return status;
      }
    }
    throw new IllegalArgumentException("ProcessingStatus not found for code: " + code);
  }
}
