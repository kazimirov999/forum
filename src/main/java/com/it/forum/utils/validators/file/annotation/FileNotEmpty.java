package com.it.forum.utils.validators.file.annotation;


import com.it.forum.utils.validators.file.constraint.FileExistenceConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Documented
@Constraint(validatedBy = FileExistenceConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotEmpty {

    String message() default "File is not exist";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
