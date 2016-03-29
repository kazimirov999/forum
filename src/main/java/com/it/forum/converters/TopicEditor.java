package com.it.forum.converters;

import com.it.forum.services.ITopicService;
import org.jboss.logging.Logger;

import java.beans.PropertyEditorSupport;


/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class TopicEditor extends PropertyEditorSupport {

    private static final Logger log = Logger.getLogger(TopicEditor.class);

    final private ITopicService topicService;

    public TopicEditor(ITopicService topicService) {
        this.topicService = topicService;
    }


    @Override
    public void setAsText(String id) throws IllegalArgumentException {
        if (id != null && !id.isEmpty()) {
            try {
                this.setValue(topicService.findTopicById(Long.parseLong(id)));
            } catch (Exception e) {
                e.printStackTrace();
                log.error("Error convert Long to String", e);
            }
        }
    }
}

