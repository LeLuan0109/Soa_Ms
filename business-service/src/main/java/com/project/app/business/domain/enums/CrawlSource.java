package com.project.app.business.domain.enums;

import lombok.Getter;

@Getter
public enum CrawlSource {
    FACEBOOK(1, "fb"),
    TIKTOK(2, "tiktok"),
    YOUTUBE(3, "yt"),
    WEBSITE(4, "web");

    private final int code;
    private final String value;

    CrawlSource(int code, String value) {
        this.code = code;
        this.value = value;
    }

}
