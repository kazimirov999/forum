package com.it.forum.controllers;

import com.it.forum.domain.entities.Section;
import com.it.forum.services.ISectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class SectionController extends AbstractGlobalController {

    @Autowired
    private ISectionService sectionService;

    @RequestMapping(value = "/section/add", method = RequestMethod.GET)
    public String showAddTopicForm(Model model, HttpSession session) {

        model.addAttribute("sectionBean", new Section());

        return "section_add_page";
    }

    @RequestMapping(value = "/section/add", method = RequestMethod.POST)
    public String addSection(@Valid @ModelAttribute("sectionBean") Section sectionBean, BindingResult result, Model model, HttpSession session) {

        if (result.hasErrors()) {
            return "section_add_page";
        }
        sectionService.saveSection(sectionBean);

        return "redirect:/";
    }

    @RequestMapping(value = "/section/delete/{sectionId}", method = RequestMethod.GET)
    public String deleteSection(@PathVariable Long sectionId, HttpSession session) {

        sectionService.deleteSectionById(sectionId);

        return "redirect:" + (String) session.getAttribute("lastUri");
    }
}
