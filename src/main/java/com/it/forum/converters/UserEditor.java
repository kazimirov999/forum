package com.it.forum.converters;

import com.it.forum.services.IUserService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;


/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class UserEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(UserEditor.class);

    final private IUserService userService;

    public UserEditor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(userService.findUserById(Long.parseLong(id)));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error convert Long to String", e);
            }
        }
    }


}

