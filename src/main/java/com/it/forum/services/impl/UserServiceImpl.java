package com.it.forum.services.impl;

import com.it.forum.exceptions.UserNotFoundException;
import com.it.forum.domain.dao.IUserDao;
import com.it.forum.domain.entities.User;
import com.it.forum.domain.enumx.Role;
import com.it.forum.security.CustomUserDetails;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Transactional
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> findAllUsers() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            userDao.initialize(user.getCountry());
        }
        return users;
    }

    @Override
    public User findUserByLogin(String login) {

        return userDao.findUserByLogin(login);
    }

    @Override
    public User findUserByEmail(String email) {

        return userDao.findUserByEmail(email);
    }

    @Override
    public User findUserById(Long userId) {
        User user = userDao.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("User is not exist");
        }

        return user;
    }

    @Override
    public byte[] getPhotoByUserId(Long userId) {
        User user = null;
        try {
            user = userDao.findById(userId);
            if (user.getPhoto() == null) {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            return new byte[0];
        }

        return user.getPhoto();
    }

    @Override
    public void saveUser(User user) {

        userDao.saveOrUpdate(user);
    }

    @Override
    public Boolean loginIsExist(String login) {

        return (findUserByLogin(login) != null);
    }

    @Override
    public Boolean emailIsExist(String email) {

        return (findUserByEmail(email) != null);
    }

    @Transactional(readOnly = false)
    @Override
    public void activateUser(Long userId) {
        User user = findUserById(userId);
        user.setEnable(true);
        userDao.edit(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void deactivateUser(Long userId) {
        User user = findUserById(userId);
        user.setEnable(false);
        userDao.edit(user);
    }

    @Transactional(readOnly = false)
    @Override
    public void setUserRoleAdmin(Long userId) {
        User user = findUserById(userId);
        user.setRole(Role.ROLE_ADMIN);
        userDao.edit(user);
    }

    @Transactional(readOnly = false)
    @Override
    public Boolean deleteUserById(Long userId) {
        User user = findUserById(userId);

        return userDao.delete(user);
    }

    @Override
    public Long countAllUsers() {
        return userDao.countAllUsers();
    }

    @Override
    public User getInformationOfCurrentUser() {
        User user = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal();
            user = principal.getUser();
        }

        return user;
    }
}
