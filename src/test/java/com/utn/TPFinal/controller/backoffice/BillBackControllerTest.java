package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.projections.BillProjection;

import com.utn.TPFinal.projections.ClientProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BillBackControllerTest {

    private BillBackController billBackController;
    private BillProjection billProjection;
    private ClientProjection clientProjection;

    @Mock
    private BillController billController;

    @Mock
    private PhoneLineController phoneLineController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        billBackController = new BillBackController(billController, phoneLineController);
        ProjectionFactory factoryBill = new SpelAwareProxyProjectionFactory();
        billProjection = factoryBill.createProjection(BillProjection.class);

        ProjectionFactory factoryClient = new SpelAwareProxyProjectionFactory();
        clientProjection = factoryClient.createProjection(ClientProjection.class);
    }

    @Test
    void getBillAllOk(){
       billProjection.setComplete_name("Santiago Labatut");
       billProjection.setDni("41686701");
       billProjection.setFull_number("02235351545");
       billProjection.setCalls_quantity(5);
       billProjection.setTotal_price((long) 300);
       billProjection.setTotal_cost((long) 500);
       billProjection.setBill_date(new Date());
       billProjection.setExpiration_date(new Date());

        List<BillProjection> bills = new ArrayList<BillProjection>();
        bills.add(billProjection);

        when(billController.getBillAll()).thenReturn(bills);

        ResponseEntity<List<BillProjection>> response = billBackController.getBillAll();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bills, response.getBody());
    }

    @Test
    void getAllBillEmpty() {
        List<BillProjection> billList = new ArrayList<BillProjection>();

        when(billController.getBillAll()).thenReturn(billList);
        ResponseEntity<List<BillProjection>> response = billBackController.getBillAll();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getBillbyNumberOk() {
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineController.getClientLine(clientProjection.getDni())).thenReturn(clientProjection);

        billProjection.setComplete_name("Santiago Labatut");
        billProjection.setDni("41686701");
        billProjection.setFull_number("02235351545");
        billProjection.setCalls_quantity(5);
        billProjection.setTotal_price((long) 300);
        billProjection.setTotal_cost((long) 500);
        billProjection.setBill_date(new Date());
        billProjection.setExpiration_date(new Date());

        List<BillProjection> bills = new ArrayList<BillProjection>();
        bills.add(billProjection);

        when(billController.getBillByNumber(clientProjection.getDni())).thenReturn(bills);

        ResponseEntity<List<BillProjection>> response = billBackController.getBillbyNumber(clientProjection.getDni());
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(bills, response.getBody());
    }

    @Test
    void getBillByNumberEmpty() {
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineController.getClientLine(clientProjection.getDni())).thenReturn(clientProjection);

        List<BillProjection> billList = new ArrayList<BillProjection>();

        when(billController.getBillByNumber(clientProjection.getDni())).thenReturn(billList);
        ResponseEntity<List<BillProjection>> response = billBackController.getBillbyNumber(clientProjection.getDni());

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getBillByNumberBad() {
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineController.getClientLine(clientProjection.getDni())).thenReturn(null);

        ResponseEntity<List<BillProjection>> response = billBackController.getBillbyNumber(clientProjection.getDni());

        assertEquals(404, response.getStatusCodeValue());
    }
}