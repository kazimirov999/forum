package com.it.forum.controllers;

import com.it.forum.utils.ServiceMessage;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


/**
 * @author: Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Controller
public class SecurityController extends AbstractGlobalController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        HttpSession session, RedirectAttributes redirect) {

        if (error != null) {
            ServiceMessage.write(redirect, "errorAuth", "Authentication is failure");
            return "redirect:"+(String)session.getAttribute("lastUri");
        }
        if (logout != null) {
            if (session != null) {
                session.invalidate();
            }
            SecurityContextHolder.clearContext();
            return "redirect:/";
        }

        return "authentication_page";
    }
}
