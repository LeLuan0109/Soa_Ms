package com.project.app.business.domain.enums;

public enum Spam {
  SPAM(1),
  NO_SPAM(0);

  private final int value;

  // Constructor để gán giá trị
  Spam(int value) {
    this.value = value;
  }

  // Phương thức getter để lấy giá trị
  public int getValue() {
    return value;
  }

  // Phương thức để lấy enum từ giá trị integer (optional)
  public static Spam fromValue(int value) {
    for (Spam spam : Spam.values()) {
      if (spam.value == value) {
        return spam;
      }
    }
    throw new IllegalArgumentException("Unexpected value: " + value);
  }
}
