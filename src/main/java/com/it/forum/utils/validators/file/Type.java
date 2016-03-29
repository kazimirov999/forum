package com.it.forum.utils.validators.file;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public enum Type {

    JPEG("image/jpeg"), GIF("image/gif"), BMP("image/bmp"),
    JPG("image/jpg");
    private String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
