package com.it.forum.controllers;

import com.it.forum.services.IContentService;
import com.it.forum.utils.ServiceMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class IndexController extends AbstractGlobalController {

    @Autowired
    private IContentService contentService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showIndexPage(ModelMap model){

        model.addAttribute("sectionInfo", contentService.getSectionInformation());

        return "index";
    }

    @RequestMapping(value = "/information/show", method = RequestMethod.GET)
    public String showMessage(@RequestParam(value = "message", required = false) String message, Model uiModel) {

        if (message!= null) {
            ServiceMessage.write(uiModel, message);
        }

        return "information_page";
    }

}
