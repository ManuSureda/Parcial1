package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.session.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Client;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CallWebControllerTest {
    private CallWebController callWebController;
    private CallsProjection callsProjection;
    private CallsProjection topTenCallProjection;

    @Mock
    private SessionManager sessionManager;

    @Mock
    private CallController callController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callWebController = new CallWebController(sessionManager,callController);

        ProjectionFactory factoryCall = new SpelAwareProxyProjectionFactory();
        callsProjection = factoryCall.createProjection(CallsProjection.class);

        ProjectionFactory factoryTopCall = new SpelAwareProxyProjectionFactory();
        topTenCallProjection = factoryTopCall.createProjection(CallsProjection.class);
    }

    @Test
    void getTopTenDestinationsOk() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        topTenCallProjection.setFull_name_o("Santiago Labatut");
        topTenCallProjection.setDestination_city("Mar del Plata");
        topTenCallProjection.setCant(5);

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();
        topCalls.add(topTenCallProjection);

        when(callController.getTopTenDestinations(user.getDni())).thenReturn(topCalls);
        ResponseEntity<List<CallsProjection>> response = callWebController.getTopTenDestinations(token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(topCalls, response.getBody());
    }

    @Test
    void getTopTenDestinationsEmpty() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();

        when(callController.getTopTenDestinations(user.getDni())).thenReturn(topCalls);
        ResponseEntity<List<CallsProjection>> response = callWebController.getTopTenDestinations(token);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void getCallByDateOk() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        callsProjection.setOrigin_line("0223531545");
        callsProjection.setOrigin_city("Mar del Plata");
        callsProjection.setDestination_line("02236162410");
        callsProjection.setDestination_city("Mar del Plata");
        callsProjection.setDuration((long) 400);
        callsProjection.setCall_date(new Date());
        callsProjection.setTotal_price(500);
        callsProjection.setTotal_cost((float) 0.5);

        List<CallsProjection> calls = new ArrayList<CallsProjection>();
        calls.add(callsProjection);

        when(callController.getCallByDate(user.getDni(), "2020-06-20", "2020-07-20")).thenReturn(calls);
        ResponseEntity<List<CallsProjection>> response = callWebController.getCallByDate(token,"2020-06-20", "2020-07-20" );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(calls, response.getBody());

    }

    @Test
    void getCallByDateBad() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        List<CallsProjection> calls = new ArrayList<CallsProjection>();

        when(callController.getCallByDate(user.getDni(), "2020-12-20", "2020-07-20")).thenReturn(calls);
        ResponseEntity<List<CallsProjection>> response = callWebController.getCallByDate(token,"2020-12-20", "2020-07-20" );
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void getCallByDateEmpty() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        List<CallsProjection> calls = new ArrayList<CallsProjection>();

        when(callController.getCallByDate(user.getDni(), "2020-06-20", "2020-07-20")).thenReturn(calls);
        ResponseEntity<List<CallsProjection>> response = callWebController.getCallByDate(token,"2020-06-20", "2020-07-20" );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}