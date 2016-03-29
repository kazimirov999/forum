package com.it.forum.services.impl;

import com.it.forum.exceptions.EntityNotFoundException;
import com.it.forum.domain.dao.ITopicDao;
import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Message;
import com.it.forum.domain.entities.Topic;
import com.it.forum.services.IBranchService;
import com.it.forum.services.ITopicService;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    private ITopicDao topicDao;

    @Autowired
    private IBranchService branchService;

    @Autowired
    private IUserService userService;

    @Override
    public Set<Topic> findTopicsByBranchId(Long branchId){
       List<Topic> topics = topicDao.findTopicsByBranchId(branchId);
        if(topics == null){
            throw new EntityNotFoundException("Topics is not exist");
        }
        return new TreeSet<>(topics);
    }

    @Override
    public Topic findTopicById(Long topicId){
        Topic topic = topicDao.findById(topicId);

        if(topic == null){
            throw new EntityNotFoundException("Topic is not exist");
        }

        List<Message> messages = topic.getMessages();
        List<Message> reverseMessages = new ArrayList<>(messages.size());

        for(int i=messages.size()-1; i >=0; i--){
            Message message = messages.get(i);
            topicDao.initialize(message.getComments());
            reverseMessages.add(message);
        }
        topic.setMessages(reverseMessages);

        return topic;
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteTopicById(Long topicId){
        return topicDao.delete((Topic)topicDao.findById(topicId));
    }

    @Transactional(readOnly = false)
    @Override
    public Long saveTopicInBranch(Topic topic, Long branchId) {
        Branch branch = branchService.findBranchById(branchId);
        branch.getTopics().add(topic);
        topic.setBranch(branch);

        return topicDao.save(topic);
    }
}
