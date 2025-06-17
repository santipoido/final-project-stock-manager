package com.tpfinal.stockmanager.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class RoleValidator implements ConstraintValidator<ValidRole, String> {

    private final String[] allowedRoles = {"ADMIN", "SELLER"};

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return false;
        return Arrays.stream(allowedRoles).anyMatch(r -> r.equalsIgnoreCase(value));
    }
}

