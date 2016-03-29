package com.it.forum.controllers;

import com.it.forum.domain.entities.Branch;
import com.it.forum.services.IBranchService;
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
@Controller()
public class BranchController extends AbstractGlobalController{

    @Autowired
    private IBranchService branchService;

    @RequestMapping(value = "/branch/add/{sectionId}", method = RequestMethod.GET)
    public String showAddBranchForm(@PathVariable Long sectionId, Model model, HttpSession session) {
        model.addAttribute("branchBean", new Branch());
        session.setAttribute("sectionId", sectionId);

        return "branch_add_page";
    }

    @RequestMapping(value = "/branch/add", method = RequestMethod.POST)
     public String addBranch(@Valid  @ModelAttribute("branchBean")Branch branchBean, BindingResult result,Model model, HttpSession session) {

        if (result.hasErrors()) {
            model.addAttribute("branchBean", new Branch());
            return "branch_add_page";
        }
        branchService.saveBranchInSection(branchBean, (Long)session.getAttribute("sectionId"));
        session.setAttribute("sectionId",null);

        return "redirect:/";
    }

    @RequestMapping(value = "/branch/delete/{branchId}", method = RequestMethod.GET)
    public String deleteBranch(@PathVariable Long branchId, HttpSession session) {

        branchService.deleteBranchById(branchId);

        return "redirect:" + (String) session.getAttribute("lastUri");
    }
}
