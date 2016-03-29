package com.it.forum.controllers;

import com.it.forum.converters.CommentEditor;
import com.it.forum.converters.MessageEditor;
import com.it.forum.converters.UserEditor;
import com.it.forum.domain.entities.Comment;
import com.it.forum.domain.entities.Message;
import com.it.forum.domain.entities.User;
import com.it.forum.form.beans.CommentFormBean;
import com.it.forum.services.ICommentService;
import com.it.forum.services.IMessageService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractGlobalController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private IMessageService messageService;

    private static final Logger log = Logger.getLogger(CommentController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Message.class, new MessageEditor(messageService));
        binder.registerCustomEditor(User.class, new UserEditor(userService));
        binder.registerCustomEditor(Comment.class, new CommentEditor(commentService));
    }

    @RequestMapping(value = "/edit/show", method = RequestMethod.GET)
    public String showEditCommentForm(CommentFormBean commentFormBean, Model model, HttpSession session) {
        model.addAttribute("edit", true);
        model.addAttribute("commentEditBean", commentFormBean.getComment());

        return "forward:" + (String) session.getAttribute("lastUri");
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addComment(@Valid Comment comment, BindingResult result, HttpSession session) {

        final String LAST_URI = (String) session.getAttribute("lastUri");
        if (result.hasErrors()) {
            return "redirect:" + LAST_URI;
        }
        comment.setUser(userService.getInformationOfCurrentUser());
        commentService.saveComment(comment);

        return "redirect:" + LAST_URI;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteComment(CommentFormBean commentFormBean, HttpSession session) {

        commentService.deleteComment(commentFormBean.getComment());

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editComment(@ModelAttribute("commentEditBean")Comment comment, HttpSession session) {
        try {
            comment.setUser(userService.getInformationOfCurrentUser());
            commentService.editComment(comment);
        }catch(Exception ignore){
            ignore.printStackTrace();
        }

        return "redirect:" + (String) session.getAttribute("lastUri");
    }

}