package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.repository.CallRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class CallServiceTest {

    private CallService callService;

    private CallsProjection callsProjection;
    private List<CallsProjection> calls;

    private CallsProjection topTenCallProjection;

    @Mock
    private CallRepository callRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        callService = new CallService(callRepository);

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.callsProjection = factory.createProjection(CallsProjection.class);
        this.topTenCallProjection = factory.createProjection(CallsProjection.class);

        callsProjection.setOrigin_line("02236493784");
        callsProjection.setOrigin_city("Mar del Plata");
        callsProjection.setDestination_line("02216986864");
        callsProjection.setDestination_city("La Plata");
        callsProjection.setDuration((long) 50);
        callsProjection.setTotal_price(500);
        callsProjection.setTotal_cost((float) 200);
        callsProjection.setCall_date(new Date());

        calls = new ArrayList<>();
        calls.add(callsProjection);
    }

    @Test
    void getListCall() {
        when(callRepository.getListCall("42231235")).thenReturn(calls);

        List<CallsProjection> aux = callService.getListCall("42231235");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callRepository,times(1)).getListCall("42231235");
    }

    @Test
    void addCall() {
        CallDto call = new CallDto("123","321", (long) 50,new Date());
        when(callRepository.addCall(call.getLineOrigin(),
                call.getLineDestination(),
                call.getDuration(),
                call.getCallDate())).thenReturn(1);

        Integer response = callService.addCall(call);
        assertEquals(response,1);
        verify(callRepository,times(1)).addCall(call.getLineOrigin(),
                call.getLineDestination(),
                call.getDuration(),
                call.getCallDate());
    }

    @Test
    void getTopTenDestinations() {
        topTenCallProjection.setFull_name_o("Juan Juaneado");
        topTenCallProjection.setDestination_city("Atalp led Ram");
        topTenCallProjection.setCant(5);

        List<CallsProjection> topCalls = new ArrayList<CallsProjection>();
        topCalls.add(topTenCallProjection);

        when(callRepository.getTopTenDestinations("42231235")).thenReturn(topCalls);

        List<CallsProjection> aux = callService.getTopTenDestinations("42231235");

        assertNotNull(aux);
        assertEquals(aux, topCalls);
        verify(callRepository,times(1)).getTopTenDestinations("42231235");
    }

    @Test
    void getListCallByDate() {
        when(callRepository.getListCallByDate("42231235","2020-04-01","2020-06-20")).thenReturn(calls);

        List<CallsProjection> aux = callService.getListCallByDate("42231235","2020-04-01","2020-06-20");

        assertNotNull(aux);
        assertEquals(aux, calls);
        verify(callRepository,times(1)).getListCallByDate("42231235","2020-04-01","2020-06-20");
    }
}