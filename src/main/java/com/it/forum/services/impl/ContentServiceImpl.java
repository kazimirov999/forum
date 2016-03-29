package com.it.forum.services.impl;

import com.it.forum.containers.SectionInfoHolder;
import com.it.forum.domain.dao.IMessageDao;
import com.it.forum.domain.dao.ITopicDao;
import com.it.forum.services.IContentService;
import com.it.forum.services.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ISectionService sectionService;
    @Autowired
    private IMessageDao messageDao;
    @Autowired
    private ITopicDao topicDao;

    @Override
    public SectionInfoHolder getSectionInformation() {

        SectionInfoHolder sectionInfoHolder = new SectionInfoHolder();
        sectionInfoHolder.setSections(sectionService.findAllSections());
        sectionInfoHolder.setAmountMessagesInBranches(messageDao.countMessagesInBranches());
        sectionInfoHolder.setAmountTopicsInBranches(topicDao.countTopicsInBranches());
        sectionInfoHolder.setLastMessageInBranch(new HashSet<>(messageDao.findLastMessagesInBranches()));

        return sectionInfoHolder;
    }
}

