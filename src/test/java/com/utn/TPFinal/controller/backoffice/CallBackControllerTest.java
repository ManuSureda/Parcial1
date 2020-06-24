package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.CallsProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static com.utn.TPFinal.model.UserType.Client;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class CallBackControllerTest {
    private CallBackController callBackController;
    private CallsProjection callsProjection;

    @Mock
    private CallController callController;

    @Mock
    private UserController userController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callBackController = new CallBackController(callController, userController);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        callsProjection = factory.createProjection(CallsProjection.class);
    }

    @Test
    void getCallTest(){
        User client = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Client, null);
        when(userController.getClient(client.getDni())).thenReturn(client);

        callsProjection.setOrigin_line("02235351545");
        callsProjection.setOrigin_city("Mar del Plata");
        callsProjection.setDestination_line("02236162410");
        callsProjection.setDestination_city("Buenos Aires");
        callsProjection.setDuration((long) 15);
        callsProjection.setTotal_price(20);
        callsProjection.setTotal_cost((float) 0.3);
        callsProjection.setCall_date(new Date());


        List<CallsProjection> callsProjections = new ArrayList<CallsProjection>();
        callsProjections.add(callsProjection);

        when(callController.getCall("41686701")).thenReturn(callsProjections);
        ResponseEntity<List<CallsProjection>> response = callBackController.getCall("41686701");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(callsProjections, response.getBody());
    }

    @Test
    void getCallEmpty() {
        User client = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Client, null);
        when(userController.getClient(client.getDni())).thenReturn(client);

        List<CallsProjection> callList = new ArrayList<CallsProjection>();

        when(callController.getCall(client.getDni())).thenReturn(callList);
        ResponseEntity<List<CallsProjection>> response = callBackController.getCall(client.getDni());

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getCallBad() {
        User client = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Client, null);
        when(userController.getClient(client.getDni())).thenReturn(null);

        ResponseEntity<List<CallsProjection>> response = callBackController.getCall(client.getDni());

        assertEquals(404, response.getStatusCodeValue());
    }
}

