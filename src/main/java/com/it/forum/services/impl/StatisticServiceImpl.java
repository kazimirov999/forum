package com.it.forum.services.impl;

import com.it.forum.interceptors.SessionRegisterListener;
import com.it.forum.services.IMessageService;
import com.it.forum.services.IStatisticService;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Service
public class StatisticServiceImpl implements IStatisticService {

    @Autowired
    @Qualifier("sessionRegistry")
    private SessionRegistry sessionRegistry;

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IUserService userService;

    @Override
    public Set<String> getLoginsOfActiveUsers() {
        List<Object> principals = sessionRegistry.getAllPrincipals();

        Set<String> logins = new HashSet<>();

        for (Object principal: principals) {
            if (principal instanceof org.springframework.security.core.userdetails.User) {
                logins.add(((org.springframework.security.core.userdetails.User) principal).getUsername());
            }
        }
        return logins;
    }

    @Override
    public int countActiveUsers() {
        return getLoginsOfActiveUsers().size();
    }

    @Override
    public int countActiveVisitors() {
        return SessionRegisterListener.getActiveSession().size();
    }

    @Override
    public Long countAllMessages() {
        return messageService.countAllMessages();
    }

    @Override
    public Long countRegisterUsers() {
        return userService.countAllUsers();
    }

    @Override
    public Statistic getStatistic() {
        Statistic statistic = new IStatisticService.Statistic(){};

        statistic.setActiveUsersNumber(countActiveUsers());
        statistic.setActiveVisitorsNumber(countActiveVisitors());
        statistic.setAllMessagesNumber(countAllMessages());
        statistic.setRegisterUsersNumber(countRegisterUsers());
        statistic.setLoginsOfActiveUsers(getLoginsOfActiveUsers());

        return statistic;
    }




}
