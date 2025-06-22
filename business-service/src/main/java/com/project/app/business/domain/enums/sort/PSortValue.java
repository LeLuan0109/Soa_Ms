package com.project.app.business.domain.enums.sort;

public enum PSortValue {
    NEW, OLD , INT , SBC, RANK;

    public static PSortValue fromString(String sort) {
        for (PSortValue value : PSortValue.values()) {
            if (sort.contains(value.name())) {
                return value;
            }
        }
        return null;
    }
}
