package com.project.app.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import org.hibernate.validator.constraints.UniqueElements;

public class UniqueElementsValidator implements ConstraintValidator<UniqueElements, List<String>> {

    @Override
    public void initialize(UniqueElements constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> list, ConstraintValidatorContext context) {
        if (list == null) {
            return true;
        }

        return list.size() == new HashSet<>(list).size();
    }
}
