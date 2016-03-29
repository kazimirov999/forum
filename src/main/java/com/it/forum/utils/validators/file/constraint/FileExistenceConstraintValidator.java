package com.it.forum.utils.validators.file.constraint;

import com.it.forum.utils.validators.file.annotation.FileNotEmpty;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FileExistenceConstraintValidator implements ConstraintValidator<FileNotEmpty, MultipartFile> {

    private static final Logger log = Logger.getLogger(FileExistenceConstraintValidator.class);

    @Override
    public void initialize(FileNotEmpty constraintAnnotation) {

    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        log.info("File name: "+ file.getName() +"file type: "+file.getContentType());
        return (file == null || file.isEmpty())? false:true;
    }
}
