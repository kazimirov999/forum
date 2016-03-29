package com.it.forum.exceptions;

import org.jboss.logging.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = Logger.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(SQLException.class)
    public String handleSQLException(Exception ex) {
        log.error("Exception handler", ex);

        return "system_error";
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public String handleIndexOutOfBoundsException(Exception ex) {
        log.error("Exception handler", ex);

        return "system_error";
    }

    @ExceptionHandler(IOException.class)
    public String handleIOException(Exception ex) {
        log.error("Exception handler", ex);

        return "system_error";
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public String handleEntityNotFoundException(Exception ex) {
        log.error("Exception handler", ex);

        return "404";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(Exception ex) {
        log.error("Exception handler", ex);

        return "404";
    }
}
