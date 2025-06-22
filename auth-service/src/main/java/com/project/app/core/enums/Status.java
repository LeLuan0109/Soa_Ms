package com.project.app.core.enums;

public enum Status {
    ACTIVE(1, "Active"),
    INACTIVE(0, "Inactive");

    private final int code;
    private final String description;

    // Constructor
    Status(int code, String description) {
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
    public static Status fromCode(int code) {
        for (Status status : values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Status not found for code: " + code);
    }
}