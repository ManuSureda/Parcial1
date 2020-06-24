package com.utn.TPFinal.parcial;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.web.CallWebController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.CallsProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Client;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class ParcialControllerTest {

    private ParcialController parcialController;
    private CallsProjection topTenCallProjection;

    @Mock
    private CallController callController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        parcialController = new ParcialController(callController);

        ProjectionFactory factoryTopCall = new SpelAwareProxyProjectionFactory();
        topTenCallProjection = factoryTopCall.createProjection(CallsProjection.class);
    }

    @Test
    void getTopTenDestinations() {

        topTenCallProjection.setFull_name_o("Palo Kel");
        topTenCallProjection.setDestination_city("Mar del Plata");
        topTenCallProjection.setCant(5);

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();
        topCalls.add(topTenCallProjection);

        when(callController.getTopTenDestinations("123")).thenReturn(topCalls);
        ResponseEntity<List<CallsProjection>> response = parcialController.getTopTenDestinations("123");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(topCalls, response.getBody());
    }

    @Test
    void getTopTenDestinationsEmpty() {

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();

        when(callController.getTopTenDestinations("123")).thenReturn(topCalls);
        ResponseEntity<List<CallsProjection>> response = parcialController.getTopTenDestinations("123");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }


}