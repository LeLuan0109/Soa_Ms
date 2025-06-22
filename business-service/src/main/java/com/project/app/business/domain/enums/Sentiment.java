package com.project.app.business.domain.enums;

public enum Sentiment {
  POSITIVE(1),
  NEGATIVE(2),
  NEUTRAL(0);

  private final int value;

  // Constructor to assign value
  Sentiment(int value) {
    this.value = value;
  }

  // Getter method to retrieve value
  public int getValue() {
    return value;
  }

  // Method to retrieve enum from integer value
  public static Sentiment fromValue(int value) {
    for (Sentiment sentiment : Sentiment.values()) {
      if (sentiment.value == value) {
        return sentiment;
      }
    }
    throw new IllegalArgumentException("Unexpected value: " + value);
  }
}
