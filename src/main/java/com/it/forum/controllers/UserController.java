package com.it.forum.controllers;

import com.it.forum.converters.CountryEditor;
import com.it.forum.domain.entities.Country;
import com.it.forum.domain.entities.User;
import com.it.forum.services.ICountryService;
import com.it.forum.services.IMailService;
import com.it.forum.services.IUserService;
import com.it.forum.utils.ServiceMessage;
import com.it.forum.utils.validators.UserValidator;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class UserController extends AbstractGlobalController{

    @Autowired
    private IUserService userService;
    @Autowired
    private ICountryService countryService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private IMailService mailService;
    
    private static final Logger log = Logger.getLogger(UserController.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Country.class, new CountryEditor(countryService));
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm(Model model) {
        model.addAttribute("userBean", new User());
        model.addAttribute("countries", countryService.findAllCountries());

        return "register_page";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("userBean") User user, BindingResult result, Model model, RedirectAttributes redirect) {
        userValidator.validate(user, result);

        if (result.hasErrors()) {
            model.addAttribute("countries", countryService.findAllCountries());
            return "register_page";
        }

        String serviceMessage = "Register is success";
        try {
            user.setEnable(true);
            userService.saveUser(user);
            mailService.sendEmailToUser(user);
        } catch (Exception e) {
            log.error("Register user", e);
            serviceMessage = "Register is failure";
        }
        ServiceMessage.write(redirect, serviceMessage);

        return "redirect:" + "/information/show";
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public String showUser(@PathVariable Long userId, Model model) {

        model.addAttribute("user", userService.findUserById(userId));

        return "user_view_page";
    }

    @RequestMapping(value = "/user/name/{userLogin}", method = RequestMethod.GET)
    public String showUser(@PathVariable String userLogin, Model model) {

        model.addAttribute("user", userService.findUserByLogin(userLogin));

        return "user_view_page";
    }

    @RequestMapping(value = "/users/show", method = RequestMethod.GET)
    public String showUsers(Model model) {

        model.addAttribute("users", userService.findAllUsers());

        return "manage_user_page";
    }

    @RequestMapping(value = "/user/activate/{userId}", method = RequestMethod.GET)
    public String activateUser(@PathVariable("userId") Long userId, Model model) {

        String serviceMessage = "Activate is failure";
        try {
            userService.activateUser(userId);
        } catch (Exception e) {
            log.error("Activate user", e);
            serviceMessage = "activate.failure";
        }
        ServiceMessage.write(model, serviceMessage);

        return "forward:" + "/users/show";
    }

    @RequestMapping(value = "/user/deactivate/{userId}", method = RequestMethod.GET)
    public String deactivateUser(@PathVariable("userId") Long userId, Model model) {

        String serviceMessage = "Deactivate is success";
        try {
            userService.deactivateUser(userId);
        } catch (Exception e) {
            log.error("Deactivate user", e);
            serviceMessage = "Deactivate is failure";
        }
        ServiceMessage.write(model, serviceMessage);

        return "forward:" + "/users/show";
    }

    @RequestMapping(value = "/user/do_admin/{userId}", method = RequestMethod.GET)
    public String setUserRoleAdmin(@PathVariable("userId") Long userId, Model model) {

        String serviceMessage = "Set user as admin is success";
        try {
            userService.setUserRoleAdmin(userId);
        } catch (Exception e) {
            log.error("Set role admin", e);
            serviceMessage = "Set user as admin is failure";
        }
        ServiceMessage.write(model, serviceMessage);

        return "forward:" + "/users/show";
    }

    @RequestMapping(value = "/user/delete/{userId}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("userId") Long userId, Model model) {

        String serviceMessage = "Delete is success";
        try {
            if(userService.deleteUserById(userId) == false)
                serviceMessage = "User delete is failure, message is exist!";
        } catch (Exception e) {
            log.error("Delete user", e);
            serviceMessage = "Delete is failure";
        }
        ServiceMessage.write(model, serviceMessage);

        return "forward:" + "/users/show";
    }

    public void setUserValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
}
