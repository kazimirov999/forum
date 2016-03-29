package com.it.forum.services;

import com.it.forum.domain.entities.User;

import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IUserService {

    List<User> findAllUsers();

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    User findUserById(Long userId);

    byte[] getPhotoByUserId(Long userId);

    void saveUser(User user);

    Boolean loginIsExist(String login);

    Boolean emailIsExist(String email);

    void activateUser(Long userId);

    void deactivateUser(Long userId);

    void setUserRoleAdmin(Long userId);

    Boolean deleteUserById(Long userId);

    Long countAllUsers();

    User getInformationOfCurrentUser();
}
