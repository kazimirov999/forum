package com.it.forum.services.impl;

import com.it.forum.domain.dao.ICommentDao;
import com.it.forum.domain.entities.Comment;
import com.it.forum.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentDao commentDao;

    @Override
    public Comment findCommentById(Long commentId){
        Comment comment = commentDao.findById(commentId);
        if(comment == null){
            throw new EntityNotFoundException("Comment is not exist");
        }

        return comment;
    }

    @Override
    public void saveComment(Comment comment) {
        commentDao.save(comment);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteComment(Comment comment) {
        return commentDao.delete(comment);
    }

    @Transactional(readOnly = false)
    @Override
    public Comment editComment(Comment comment){
        return commentDao.edit(comment);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteCommentById(Long commentId){
        Comment comment = commentDao.findById(commentId);
        return deleteComment(comment);
    }
}


