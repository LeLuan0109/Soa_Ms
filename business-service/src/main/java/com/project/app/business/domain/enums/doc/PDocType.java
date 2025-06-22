package com.project.app.business.domain.enums.doc;

public enum PDocType {
    POST("post", 1),
    COMMENT("comment", 2);

    private final String name;
    private final int value;

    PDocType(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static PDocType fromString(String doctype) {
        for (PDocType pDocType : PDocType.values()) {
            if (pDocType.getName().equalsIgnoreCase(doctype)) {
                return pDocType;
            }
        }
        return null;
    }

    public static Integer docTypeToNumber(String docType) {
        PDocType pDocType = PDocType.fromString(docType);
        if (pDocType != null) {
            return pDocType.getValue();
        }
        return null;
    }
}
