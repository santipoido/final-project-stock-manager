package com.tpfinal.stockmanager.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RoleValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRole {
    String message() default "Invalid role. Allowed values: ADMIN, SELLER";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
