package com.it.forum.domain.dao;


import com.it.forum.domain.entities.User;

/*
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IUserDao extends IGenericDao<User, Long> {

    User findUserByLogin(String login);

    User findUserByEmail(String login);

    Long countAllUsers();
}
