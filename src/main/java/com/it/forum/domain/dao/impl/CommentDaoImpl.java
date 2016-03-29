package com.it.forum.domain.dao.impl;

import com.it.forum.domain.dao.ICommentDao;
import com.it.forum.domain.entities.Comment;
import org.springframework.stereotype.Repository;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment, Long> implements ICommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }
}
