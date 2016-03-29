package com.it.forum.services;

import com.it.forum.domain.entities.Message;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IMessageService {

    Message findMessageById(Long messageId);

    void saveMessage(Message message);

    Boolean deleteMessageById(Long messageId);

    Long countAllMessages();
}
