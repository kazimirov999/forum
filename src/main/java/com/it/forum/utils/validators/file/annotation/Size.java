package com.it.forum.utils.validators.file.annotation;


import com.it.forum.utils.validators.file.constraint.FileSizeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Documented
@Constraint(validatedBy = FileSizeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Size {

    int min() default 0;

    int max() default 5000000;

    String message() default "Size is not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

