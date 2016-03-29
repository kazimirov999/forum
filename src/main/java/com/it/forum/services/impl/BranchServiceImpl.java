package com.it.forum.services.impl;

import com.it.forum.domain.dao.IBranchDao;
import com.it.forum.domain.entities.Branch;
import com.it.forum.domain.entities.Section;
import com.it.forum.exceptions.EntityNotFoundException;
import com.it.forum.services.IBranchService;
import com.it.forum.services.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class BranchServiceImpl implements IBranchService {

    @Autowired
    private IBranchDao branchDao;
    @Autowired
    private ISectionService sectionService;

    @Override
    public Branch findBranchById(Long branchId) {
        Branch branch = branchDao.findById(branchId);
        if(branch == null){
            throw new  EntityNotFoundException("Branch is not exist");
        }

        return branch;
    }

    @Override
    public void saveBranchInSection(Branch branch, Long sectionId){
        Section section = sectionService.findSectionById(sectionId);
        section.getBranches().add(branch);
        sectionService.saveSection(section);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteBranchById(Long branchId){

        return branchDao.delete(findBranchById(branchId));
    }
}
