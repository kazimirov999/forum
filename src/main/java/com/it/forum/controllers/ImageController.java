package com.it.forum.controllers;

import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class ImageController{

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "/user/photo/{userId}")
    public byte[] downloadUserPhoto(@PathVariable Long userId) {

        return userService.getPhotoByUserId(userId);
    }

}