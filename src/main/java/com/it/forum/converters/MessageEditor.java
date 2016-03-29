package com.it.forum.converters;

import com.it.forum.services.IMessageService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class MessageEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(MessageEditor.class);

    final private IMessageService messageService;

    public MessageEditor(IMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(messageService.findMessageById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
    }


}
