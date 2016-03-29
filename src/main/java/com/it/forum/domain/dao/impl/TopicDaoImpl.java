package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.ITopicDao;
import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Topic;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class TopicDaoImpl extends GenericDaoImpl<Topic, Long> implements ITopicDao {

    public TopicDaoImpl() {
        super(Topic.class);
    }

    @Override
    public List<Topic> findTopicsByBranchId(Long branchId){
        return   getSession()
                .createCriteria(getType())
                .add(Restrictions.eq("branch.id", branchId)).list();
    }

    @Override
    public Map<Branch, Long> countTopicsInBranches() {
        Map<Branch, Long> result = new HashMap<>();
        List<Object[]> resultList = (List<Object[]>) getHibernateTemplate()
                .findByNamedQuery("Topic.countTopicsInBranches");

        for (Object[] aRow : resultList)
            result.put((Branch) aRow[0], (Long) aRow[1]);

        return result;
    }

    @Override
    public Long save(Topic topic){
        return (Long) getSession().save(topic);
    }

}
