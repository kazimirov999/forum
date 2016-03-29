package com.it.forum.services;

import com.it.forum.domain.entities.Section;

import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ISectionService{

    void saveSection(Section section);

    Section findSectionById(Long sectionId);

    List<Section> findAllSections();

    Boolean deleteSectionById(Long sectionId);
}
