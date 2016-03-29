package com.it.forum.utils;

import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.Serializable;

/**
 * @author : Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class ServiceMessage implements Serializable {

    private static final String attribute = "serviceMessage";

    private ServiceMessage() {
    }

    public static void write(Model model, String message) {
        model.addAttribute(attribute, message);
    }

    public static void write(Model model, String customAttribute, String message) {
        model.addAttribute(customAttribute, message);
    }

    public static void write(RedirectAttributes redirect, String message) {
        redirect.addFlashAttribute(attribute, message);
    }

    public static void write(RedirectAttributes redirect, String customAttribute, String message) {
        redirect.addFlashAttribute(customAttribute, message);
    }

}
