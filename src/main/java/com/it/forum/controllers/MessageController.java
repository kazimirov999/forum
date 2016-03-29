package com.it.forum.controllers;

import com.it.forum.converters.TopicEditor;
import com.it.forum.converters.UserEditor;
import com.it.forum.domain.entities.Message;
import com.it.forum.domain.entities.Topic;
import com.it.forum.domain.entities.User;
import com.it.forum.services.IMessageService;
import com.it.forum.services.ITopicService;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller()
public class MessageController extends AbstractGlobalController {

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ITopicService topicService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(User.class, new UserEditor(userService));
        binder.registerCustomEditor(Topic.class, new TopicEditor(topicService));
    }

    @RequestMapping(value = "/message/add", method = RequestMethod.POST)
    public String addMessage(@Valid Message message, BindingResult result, HttpSession session) {
        final String LAST_URI = (String) session.getAttribute("lastUri");

        if (result.hasErrors()) {
            return "redirect:" + LAST_URI;
        }
        message.setUser(userService.getInformationOfCurrentUser());
        messageService.saveMessage(message);

        return "redirect:" + LAST_URI;
    }

    @RequestMapping(value = "/message/delete/{messageId}", method = RequestMethod.GET)
    public String deleteMessage(@PathVariable Long messageId, HttpSession session) {

        messageService.deleteMessageById(messageId);

        return "redirect:" + (String) session.getAttribute("lastUri");
    }
}
