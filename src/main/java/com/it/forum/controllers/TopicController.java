package com.it.forum.controllers;

import com.it.forum.domain.entities.Comment;
import com.it.forum.domain.entities.Message;
import com.it.forum.domain.entities.Topic;
import com.it.forum.form.beans.CommentFormBean;
import com.it.forum.services.IBranchService;
import com.it.forum.services.ITopicService;
import com.it.forum.utils.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class TopicController extends AbstractGlobalController {

    private final int ORDER_LIST_PAGE_SIZE = 3;
    @Autowired
    private ITopicService topicService;
    @Autowired
    private IBranchService branchService;

    @RequestMapping(value = "/branch/{branchId}/page/{pageNumber}", method = RequestMethod.GET)
    public String showTopics(@PathVariable Long branchId, @PathVariable int pageNumber, ModelMap model) throws Exception {

        final String BASE_URI = "/branch/" + branchId + "/page/";

        PagedListHolder<Topic> pagedListHolder =
                new PagedListHolder<Topic>(new ArrayList<>(topicService.findTopicsByBranchId(branchId)));
        pagedListHolder.setPageSize(ORDER_LIST_PAGE_SIZE);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pagedListHolder.getPageCount() && goToPage >= 0) {
            pagedListHolder.setPage(goToPage);
        }
        model.addAttribute("currentBranch", branchService.findBranchById(branchId));
        model.addAttribute("pager", Pager.currentPage(pagedListHolder, BASE_URI));
        model.addAttribute("holderTopics", pagedListHolder);

        return "topics_page";
    }

    @RequestMapping(value = "/topic/{topicId}", method = RequestMethod.GET)
    public String showTopic(@PathVariable Long topicId, Model model) {
        System.out.println(topicService.findTopicById(topicId));
        model.addAttribute("topic", topicService.findTopicById(topicId));
        model.addAttribute("commentBean", new Comment());
        model.addAttribute("commentFormBean", new CommentFormBean());
        model.addAttribute("messageBean", new Message());

        return "topic_page";
    }

    @RequestMapping(value = "/topic/add/{branchId}", method = RequestMethod.GET)
    public String showAddTopic(@PathVariable Long branchId, Model model, HttpSession session) {

        model.addAttribute("topicBean", new Topic());
        session.setAttribute("branchId", branchId);

        return "topic_add_page";
    }

    @RequestMapping(value = "/topic/add", method = RequestMethod.POST)
    public String addTopic(@Valid @ModelAttribute("topicBean") Topic topicBean, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "topic_add_page";
        }
        topicBean.setUser(userService.getInformationOfCurrentUser());
        Long topicId = topicService.saveTopicInBranch(topicBean, (Long) session.getAttribute("branchId"));

        return "redirect:" + "/topic/"+topicId;
    }

    @RequestMapping(value = "/topic/delete/{topicId}", method = RequestMethod.GET)
    public String deleteBranch(@PathVariable Long topicId, HttpSession session) {

        topicService.deleteTopicById(topicId);

        return "redirect:" + (String) session.getAttribute("lastUri");
    }
}
