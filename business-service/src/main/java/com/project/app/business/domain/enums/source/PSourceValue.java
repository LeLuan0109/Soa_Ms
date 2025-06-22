package com.project.app.business.domain.enums.source;

public enum PSourceValue {
    FACEBOOK_VALUE(1),
    TIKTOK_VALUE(2),
    YOUTUBE_VALUE(3),
    WEBSITE_VALUE(4),
    LINKEDIN_VALUE(5);

    private final int value;

    PSourceValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
