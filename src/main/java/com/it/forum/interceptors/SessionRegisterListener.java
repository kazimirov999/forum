package com.it.forum.interceptors;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Oleksandr Kazimirov (kazimirov.oleksandr@gmail.com)
 */
public class SessionRegisterListener implements HttpSessionListener {

    private static Map<String, HttpSession> activeSessions = new ConcurrentHashMap<>();

    public static Map<String, HttpSession> getActiveSession(){
        return activeSessions;
    }

    @Override
    public void sessionCreated(HttpSessionEvent event0) {
        activeSessions.put(event0.getSession().getId(), event0.getSession());
        event0.getSession().setMaxInactiveInterval(15*60);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event1) {
        activeSessions.remove(event1.getSession().getId());
    }


}
