package com.it.forum.utils.validators.file.annotation;

import com.it.forum.utils.validators.file.Type;
import com.it.forum.utils.validators.file.constraint.FileTypeConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Documented
@Constraint(validatedBy = FileTypeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface File {

    Type type()[] default Type.JPG;

    String message() default "Type is not correct!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
