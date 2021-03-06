package com.it.forum.domain.enumx;

/**
 * Enum contains types of user roles.
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * @created 12.02.2016 8:42
 */
public enum Role {

    ROLE_ADMIN("ADMIN"), ROLE_USER("USER"), ROLE_MODERATOR("MODERATOR");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
