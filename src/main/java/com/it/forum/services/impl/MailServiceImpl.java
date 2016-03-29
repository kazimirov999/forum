package com.it.forum.services.impl;

import com.it.forum.domain.entities.User;
import com.it.forum.services.IMailService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Service to work with the Mail, using MailSender.
 * .<p>
 * MailSender is annotated for automatic resource injection.
 * </p>
 *
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
@Service
public class MailServiceImpl implements IMailService {

    @Autowired
    private MailSender mailSender;

    private static final Logger log = Logger.getLogger(MailServiceImpl.class);

    @Override
    public void sendMail(String to, String subject, String body) throws MailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmailToUser(final User user) {
        final StringBuilder body = new StringBuilder("Your registration in the ForumIT is success - ")
                .append("login: ").append(user.getLogin())
                .append("Password: ").append(user.getPasswordTmp())
                .append("Date register: ").append(user.getDateRegister());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sendMail(user.getEmail(), "ForumIT register", body.toString());
                } catch (MailException e) {
                    log.error("Send mail error", e);
                    e.printStackTrace();
                }
            }
        }).start();
    }
}