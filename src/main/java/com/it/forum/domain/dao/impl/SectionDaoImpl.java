package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.ISectionDao;
import com.it.forum.domain.entities.Section;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class SectionDaoImpl extends GenericDaoImpl<Section, Long> implements ISectionDao {

    public SectionDaoImpl() {
        super(Section.class);
    }


}
