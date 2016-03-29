package com.it.forum.controllers;

import com.it.forum.domain.entities.User;
import com.it.forum.services.IStatisticService;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public abstract class AbstractGlobalController {

    @Autowired
    protected IStatisticService statisticService;
    @Autowired
    protected IUserService userService;


    @ModelAttribute("userDetails")
    public User getInformationOfVisitor() {
        return userService.getInformationOfCurrentUser();
    }

    @ModelAttribute("statisticHolder")
    public IStatisticService.Statistic getStatistics() {
        return statisticService.getStatistic();
    }


}
