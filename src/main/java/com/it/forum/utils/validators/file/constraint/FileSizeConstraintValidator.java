package com.it.forum.utils.validators.file.constraint;

import com.it.forum.utils.validators.file.annotation.Size;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FileSizeConstraintValidator implements ConstraintValidator<Size, MultipartFile> {

    private static final Logger log = Logger.getLogger(FileSizeConstraintValidator.class);

    private int min;
    private  int max;
    @Override
    public void initialize(Size size) {
        this.min = size.min();
        this.max = size.max();
    }

    @Override
    public boolean isValid( MultipartFile file , ConstraintValidatorContext cxt) {
        if(file == null || file.isEmpty()) return true;
        if(file.getSize() < min || max < file.getSize())return false;
        log.info("File name: "+ file.getName() +"file size: "+file.getSize());
        return true;
    }

}
