package com.it.forum.converters;

import com.it.forum.services.ICommentService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class CommentEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(CommentEditor.class);

    final private ICommentService commentService;

    public CommentEditor(ICommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(commentService.findCommentById(Long.parseLong(id)));
            } catch (Exception e) {
                log.error("Error convert Long to String", e);
            }
        }
    }


}
