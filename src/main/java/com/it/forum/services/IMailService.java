package com.it.forum.services;

import com.it.forum.domain.entities.User;
import org.springframework.mail.MailException;

/**
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public interface IMailService {

    /**
     * Sends message
     *
     * @param to      - email recipient
     * @param subject - subject of email
     * @param body    - body of email
     * @throws MailException
     */
    void sendMail(String to, String subject, String body) throws MailException;

    void sendEmailToUser(User user);
}
