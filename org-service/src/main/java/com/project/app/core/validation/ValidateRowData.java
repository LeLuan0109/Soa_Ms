package com.project.app.core.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Map;

public class ValidateRowData {

    public <T> void validateRowData(Map<String, Object> rowData, Class<T> clazz, int rowIndex) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();

            Object value = rowData.get(fieldName);

            // Kiểm tra nếu trường có @NotNull
            if (field.isAnnotationPresent(NotNull.class) && value == null) {
                throw new IllegalArgumentException("Hàng " + rowIndex + ": Cột " + fieldName + " không được để trống.");
            }

            // Kiểm tra nếu trường có @NotEmpty
            if (field.isAnnotationPresent(NotEmpty.class) && (value == null || value.toString().isEmpty())) {
                throw new IllegalArgumentException("Hàng " + rowIndex + ": Cột " + fieldName + " không được để trống.");
            }

            // Kiểm tra nếu trường có @NotBlank
            if (field.isAnnotationPresent(NotBlank.class) && (value == null || value.toString().trim().isEmpty())) {
                throw new IllegalArgumentException("Hàng " + rowIndex + ": Cột " + fieldName + " không được để trống.");
            }
        }
    }
}
