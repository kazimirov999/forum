package com.it.forum.exceptions;

/**
 * @author  Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }


}