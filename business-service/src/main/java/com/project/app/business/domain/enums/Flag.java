package com.project.app.business.domain.enums;

public enum Flag {
  FLAG(1),
  NO_FLAG(0);

  private final int value;

  // Constructor to assign the value
  Flag(int value) {
    this.value = value;
  }

  // Getter method to retrieve the value
  public int getValue() {
    return value;
  }

  // Optionally, you can add a method to get the enum from an integer value
  public static Flag fromValue(int value) {
    for (Flag completed : Flag.values()) {
      if (completed.value == value) {
        return completed;
      }
    }
    throw new IllegalArgumentException("Unexpected value: " + value);
  }
}
