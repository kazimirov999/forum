package com.it.forum.utils.validators;

/**
 * Author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 * Date: 11.02.2016 22:09
 */

import com.it.forum.domain.entities.User;
import com.it.forum.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Component
public class UserValidator implements Validator {

    @Autowired
    private IUserService userService;

    public boolean supports(Class clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Email is required", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login", "Login is required", "Field is required.");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordTmp", "Login is required", "Field is required.");

        if (user.getPasswordTmp() != null && user.getPasswordCheck() != null
                && !user.getPasswordTmp().equalsIgnoreCase(user.getPasswordCheck())) {
            errors.rejectValue("passwordTmp", "password is not correct", "Password is not correct");
        }
        if (user.getLogin() != null && userService.loginIsExist(user.getLogin())) {
            errors.rejectValue("login", "Login is exist", "Login is exist");
        }
        if (user.getEmail() != null && userService.emailIsExist(user.getEmail())) {
            errors.rejectValue("email", "Email is exist", "Email is exist");
        }
    }
}
