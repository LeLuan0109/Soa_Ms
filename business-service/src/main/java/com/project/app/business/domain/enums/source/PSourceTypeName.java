package com.project.app.business.domain.enums.source;

public enum PSourceTypeName {
  FACEBOOK_PAGE_NAME("facebookPage"),
  FACEBOOK_GROUP_NAME("facebookGroup"),
  FACEBOOK_USER_NAME("facebookUser"),
  TIKTOK_NAME("tiktok"),
  YOUTUBE_NAME("youtube"),
  WEBSITE_NAME("website"),
  LINKEDIN_NAME("linkedin");

  private final String name;

  PSourceTypeName(String name) {
    this.name = name;
  }

  public static PSourceTypeName fromString(String source) {
    for (PSourceTypeName pSourceName : PSourceTypeName.values()) {
      if (pSourceName.getName().equalsIgnoreCase(source)) {
        return pSourceName;
      }
    }
    return null;
  }

  public String getName() {
    return name;
  }
}
