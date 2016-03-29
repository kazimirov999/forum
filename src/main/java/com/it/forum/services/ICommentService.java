package com.it.forum.services;

import com.it.forum.domain.entities.Comment;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface ICommentService {

    Comment findCommentById(Long commentId);

    void saveComment(Comment comment);

    Boolean deleteComment(Comment comment);

    Comment editComment(Comment comment);

    Boolean deleteCommentById(Long commentId);
}
