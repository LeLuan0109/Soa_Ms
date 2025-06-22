package com.project.app.core.enums;

public enum ApprovalStatus {
    WAITING_APPROVAL(0, "WAITING_FOR_APPROVAL"),
    ASSOCIATE_APPROVED(1, "ASSOCIATE_APPROVED"),
    APPROVER_APPROVED(2, "APPROVER_APPROVED"),
    ASSOCIATE_REJECTED(3, "ASSOCIATE_REJECTED"),
    APPROVER_REJECTED(4, "APPROVER_REJECTED"),
    ;

    private final Integer code;
    private final String value;

    ApprovalStatus(Integer code, String value) {
        this.value = value;
        this.code = code;
    }
}
