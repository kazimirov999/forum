package com.it.forum.domain.dao;

import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Topic;

import java.util.List;
import java.util.Map;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ITopicDao extends IGenericDao<Topic, Long>{

    List<Topic> findTopicsByBranchId(Long branchId);

    Map<Branch, Long> countTopicsInBranches();

    Long save(Topic topic);
}
