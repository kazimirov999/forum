package com.it.forum.utils.validators.file.constraint;

import com.it.forum.utils.validators.file.Type;
import com.it.forum.utils.validators.file.annotation.File;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class FileTypeConstraintValidator implements ConstraintValidator<File, MultipartFile> {

    private static final Logger log = Logger.getLogger(FileTypeConstraintValidator.class);

    private Type[]type;

    @Override
    public void initialize(File image) {
        this.type = (image == null)? null : image.type();
    }

    @Override
    public boolean isValid( MultipartFile file , ConstraintValidatorContext cxt) {
        if(file == null || file.isEmpty()) return true;
        for(Type t: type)
        if(file.getContentType().equalsIgnoreCase(t.getType())) return true;
        log.info("File name: "+ file.getName() +"file type: "+file.getContentType());
        return false;
    }

}
