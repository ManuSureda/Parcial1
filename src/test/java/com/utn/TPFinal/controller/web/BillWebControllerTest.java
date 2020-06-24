package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.BillProjection;
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

class BillWebControllerTest {
    private BillWebController billWebController;
    private BillProjection billProjection;

    @Mock
    private SessionManager sessionManager;

    @Mock
    private BillController billController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        billWebController = new BillWebController(sessionManager,billController);

        ProjectionFactory factoryCall = new SpelAwareProxyProjectionFactory();
        billProjection = factoryCall.createProjection(BillProjection.class);
    }

    @Test
    void getBillDate() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        billProjection.setComplete_name("Santiago Labatut");
        billProjection.setDni("41686701");
        billProjection.setFull_number("02235351545");
        billProjection.setCalls_quantity(6);
        billProjection.setTotal_price(30);
        billProjection.setTotal_cost(50);
        billProjection.setBill_date(new Date());
        billProjection.setExpiration_date(new Date());

        List<BillProjection> bill = new ArrayList<BillProjection>();
        bill.add(billProjection);

        when(billController.getBillDate(user.getDni(), "2020-06-20", "2020-06-29" )).thenReturn(bill);
        ResponseEntity<List<BillProjection>> response = billWebController.getBillDate(token, "2020-06-20", "2020-06-29" );
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bill, response.getBody());
    }

    @Test
    void getBillDateEmpty() {
        User user = new User(16,"Santiago", "Labatut", "41686701", "santi", 1,null, Client, null);
        String token = sessionManager.createSession(user);
        when(sessionManager.getCurrentUser(token)).thenReturn(user);

        List<BillProjection> bill = new ArrayList<BillProjection>();

        when(billController.getBillDate(user.getDni(), "2020-06-20", "2020-06-29" )).thenReturn(bill);
        ResponseEntity<List<BillProjection>> response = billWebController.getBillDate(token, "2020-06-20", "2020-06-29" );
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}