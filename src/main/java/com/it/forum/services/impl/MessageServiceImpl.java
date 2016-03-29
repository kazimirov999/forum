package com.it.forum.services.impl;

import com.it.forum.domain.dao.IMessageDao;
import com.it.forum.domain.dao.ITopicDao;
import com.it.forum.domain.entities.Message;
import com.it.forum.exceptions.EntityNotFoundException;
import com.it.forum.services.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private ITopicDao topicDao;

    @Override
    public Message findMessageById(Long messageId) {
        Message message = messageDao.findById(messageId);
        if(message == null){
            throw new EntityNotFoundException("Message is not exist");
        }
        return message;
    }

    @Transactional(readOnly = false)
    @Override
    public void saveMessage(Message message) {
        messageDao.save(message);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteMessageById(Long messageId) {
        Message message = findMessageById(messageId);

        return messageDao.delete(message);
    }

    @Override
    public Long countAllMessages(){
        return messageDao.countAllMessages();
    }
}
