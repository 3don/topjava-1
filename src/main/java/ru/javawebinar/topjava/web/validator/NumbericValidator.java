package ru.javawebinar.topjava.web.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NumbericValidator implements ConstraintValidator<Numeric, String> {
    @Override
    public void initialize(Numeric Number) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]");
    }

}

