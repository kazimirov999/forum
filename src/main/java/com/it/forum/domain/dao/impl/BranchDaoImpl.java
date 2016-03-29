package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.IBranchDao;
import com.it.forum.domain.entities.Branch;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class BranchDaoImpl extends GenericDaoImpl<Branch, Long> implements IBranchDao {

    public BranchDaoImpl() {
        super(Branch.class);
    }

}
