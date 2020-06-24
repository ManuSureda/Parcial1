package com.utn.TPFinal.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class SessionSchedulerTest {
    SessionScheduler sessionScheduler;

    @Mock
    SessionManager sessionManager;
    @BeforeEach
    void setUp() {
        initMocks(this);
        sessionScheduler = new SessionScheduler(sessionManager);
    }

    @Test
    void expiresSessions() {
        sessionScheduler.expiresSessions();
        verify(sessionManager, times(1)).expireSessions();
    }
}