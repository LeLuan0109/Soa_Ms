package com.project.app.business.domain.enums.source;

public enum PSourceTypeValue {
    FACEBOOK_USER_VALUE(1),
    FACEBOOK_PAGE_VALUE(2),
    FACEBOOK_GROUP_VALUE(3),

    TIKTOK_USER_VALUE(5),

    YOUTUBE_USER_VALUE(4),

    NEWS_MAINSTREAM_VALUE(1),
    NEWS_LOCAL_VALUE(8),

    LINKEDIN_USER_VALUE(9),
    LINKEDIN_PAGE_VALUE(2);

    private final int value;

    PSourceTypeValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
