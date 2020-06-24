package com.utn.TPFinal.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class SessionFilterTest {
    SessionFilter sessionFilter;
    @Mock
    SessionManager sessionManager;

    @BeforeEach
    void setUp() {
        initMocks(this);
        sessionFilter = new SessionFilter();
    }
//    @Test
//    void doFilterInternal() throws ServletException, IOException {
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//        FilterChain filterChain = null;
//        sessionFilter.doFilterInternal(request, response, filterChain);
//
//        assertEquals(response.getStatus(), HttpStatus.FORBIDDEN.value());
//    }
}