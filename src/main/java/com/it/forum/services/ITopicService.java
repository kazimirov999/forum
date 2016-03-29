package com.it.forum.services;

import com.it.forum.domain.entities.Topic;

import java.util.Set;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ITopicService {

    Set<Topic> findTopicsByBranchId(Long branchId);

    Topic findTopicById(Long topicId);

    Boolean deleteTopicById(Long topicId);

    Long saveTopicInBranch(Topic topic, Long branchId);
}
