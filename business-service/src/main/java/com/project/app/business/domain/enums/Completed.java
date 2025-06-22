package com.project.app.business.domain.enums;

public enum Completed {
  COMPLETED(1),
  NO_COMPLETED(0);

  private final int value;

  // Constructor to assign the value
  Completed(int value) {
    this.value = value;
  }

  // Getter method to retrieve the value
  public int getValue() {
    return value;
  }

  // Optionally, you can add a method to get the enum from an integer value
  public static Completed fromValue(int value) {
    for (Completed completed : Completed.values()) {
      if (completed.value == value) {
        return completed;
      }
    }
    throw new IllegalArgumentException("Unexpected value: " + value);
  }
}
