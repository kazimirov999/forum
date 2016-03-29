package com.it.forum.services.impl;

import com.it.forum.domain.dao.ISectionDao;
import com.it.forum.domain.entities.Section;
import com.it.forum.domain.enumx.OrderType;
import com.it.forum.exceptions.EntityNotFoundException;
import com.it.forum.services.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class SectionServiceImpl implements ISectionService {

    @Autowired
    private ISectionDao sectionDao;

    @Transactional(readOnly = false)
    @Override
    public void saveSection(Section section){
        sectionDao.save(section);
    }

    @Override
    public Section findSectionById(Long sectionId){
        Section section = sectionDao.findById(sectionId);
        if(section == null){
            throw new EntityNotFoundException("Section is not exist");
        }
        return section;
    }

    @Override
    public List<Section> findAllSections(){
        return sectionDao.findAll("name", OrderType.ASC);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteSectionById(Long sectionId){

        return sectionDao.delete(findSectionById(sectionId));
    }
}
