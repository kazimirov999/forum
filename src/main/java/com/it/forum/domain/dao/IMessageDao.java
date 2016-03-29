package com.it.forum.domain.dao;


import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Message;

import java.util.List;
import java.util.Map;

/*
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IMessageDao extends IGenericDao<Message, Long> {

    Map<Branch, Long> countMessagesInBranches();

    List<Message> findLastMessagesInBranches();

    Long countAllMessages();
}
