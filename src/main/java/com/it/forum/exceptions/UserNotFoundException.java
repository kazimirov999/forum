package com.it.forum.exceptions;

/**
 * @author  Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }
}
