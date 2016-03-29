package com.it.forum.services;

import com.it.forum.domain.entities.Branch;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IBranchService {

    Branch findBranchById(Long branchId);

    void saveBranchInSection(Branch branch, Long sectionId);

    Boolean deleteBranchById(Long branchId);
}
