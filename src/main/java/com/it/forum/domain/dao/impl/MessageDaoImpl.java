package com.it.forum.domain.dao.impl;


import com.it.forum.domain.dao.IMessageDao;
import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Message;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class MessageDaoImpl extends GenericDaoImpl<Message, Long> implements IMessageDao {

    public MessageDaoImpl() {
        super(Message.class);
    }

    @Override
      public Map<Branch, Long> countMessagesInBranches(){
        Map<Branch, Long> result = new HashMap<>();
        List<Object[]> resultList = (List<Object[]>) getHibernateTemplate()
                .findByNamedQuery("Message.countMessagesInBranches");

        for (Object[] aRow : resultList)
            result.put((Branch)aRow[0], (Long)aRow[1]);

        return result;
    }

    @Override
    public List<Message> findLastMessagesInBranches(){
        return  (List<Message>) getHibernateTemplate().findByNamedQuery("Message.findLastMassagesInBranches");
    }

    @Override
    public Long countAllMessages(){
        return (Long) getSession().createCriteria(getType())
                .setProjection(Projections.rowCount())
                .uniqueResult();
    }
}
