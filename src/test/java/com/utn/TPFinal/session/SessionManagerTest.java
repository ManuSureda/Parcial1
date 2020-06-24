package com.utn.TPFinal.session;


import com.utn.TPFinal.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import static com.utn.TPFinal.model.UserType.Admin;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.MockitoAnnotations.initMocks;


class SessionManagerTest {
    SessionManager sessionManager;

    @Mock
    Session session;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sessionManager = new SessionManager();
    }

    @Test
    void createSession() {
        User user = new User();
        String token = sessionManager.createSession(user);
        sessionManager.sessionMap.put(token, new Session(token, user, new Date(System.currentTimeMillis())));
    }

    @Test
    void getSession() {
        String token = UUID.randomUUID().toString();
        User user = new User();
        Session aux = new Session(token, user, new Date(System.currentTimeMillis()));
        sessionManager.sessionMap.put(token, aux);
        Session aux2 = sessionManager.getSession(token);

        assertNotNull(aux2);
        assertEquals(aux, aux2);

    }

    @Test
    void getSessionBad() {
        String token = null;
        Session session = sessionManager.getSession(token);

        assertEquals(session, token);

    }

    @Test
    void removeSession() {
        String token = UUID.randomUUID().toString();
        sessionManager.sessionMap.remove(token);

        sessionManager.removeSession(token);
    }

    @Test
    void expireSessions() {

    }

    @Test
    void getCurrentUser() {
        String token = UUID.randomUUID().toString();
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        Session aux = new Session(token, user, new Date(System.currentTimeMillis()));
        sessionManager.sessionMap.put(token, aux);

         User user2 = sessionManager.getCurrentUser(token);

         assertNotNull(user2);
         assertEquals(user, user2);
    }
}