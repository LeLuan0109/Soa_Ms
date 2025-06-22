package com.project.app.business.domain.enums;

public enum SourceType {
  FACEBOOK("facebook"),
  TIKTOK("tiktok"),
  YOUTUBE("youtube"),
  NEWS("news"),
  LINKEDIN("linkedin");

  private final String value;

  SourceType(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public static SourceType fromValue(String value) {
    for (SourceType source : SourceType.values()) {
      if (source.getValue().equalsIgnoreCase(value)) {
        return source;
      }
    }
    throw new IllegalArgumentException("Invalid source type: " + value);
  }
}
