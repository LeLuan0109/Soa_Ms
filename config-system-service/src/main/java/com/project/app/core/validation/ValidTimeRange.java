package com.project.app.core.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TimeRangeValidator.class)
@Target({ElementType.TYPE}) // Annotation áp dụng cho class
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTimeRange {
  String message() default "Khoảng thời gian không hợp lệ: startDate và endDate phải cách nhau tối đa 1 năm";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}
