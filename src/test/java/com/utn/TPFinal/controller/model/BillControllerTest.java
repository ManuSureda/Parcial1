package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.BillService;
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

class BillControllerTest {

    private BillController billController;
    private BillProjection billProjection;
    private List<BillProjection> billList;

    @Mock
    private BillService billService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        billController = new BillController(billService);

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.billProjection = factory.createProjection(BillProjection.class);

        billProjection.setComplete_name("Palo Kell");
        billProjection.setDni("42231235");
        billProjection.setFull_number("02236493784");
        billProjection.setCalls_quantity(1);
        billProjection.setTotal_price(500);
        billProjection.setTotal_cost(200);
        billProjection.setBill_date(new Date());
        billProjection.setExpiration_date(new Date());


        billList = new ArrayList<>();
        billList.add(billProjection);
    }

    @Test
    void getBill() {

        when(billService.getBillByNumber("02236493784")).thenReturn(billList);

        List<BillProjection> aux = billController.getBillByNumber("02236493784");

        assertNotNull(aux);
        assertEquals(aux, billList);
        verify(billService,times(1)).getBillByNumber("02236493784");

    }

    @Test
    void getBillAll() {

        when(billService.getBillAll()).thenReturn(billList);

        List<BillProjection> aux = billController.getBillAll();

        assertNotNull(aux);
        assertEquals(aux, billList);
        verify(billService,times(1)).getBillAll();
    }

    @Test
    void getBillDate() {
        when(billService.getListBillByDate("42231235","2020-06-16","2020-06-18")).thenReturn(billList);

        List<BillProjection> aux = billController.getBillDate("42231235","2020-06-16","2020-06-18");

        assertNotNull(aux);
        assertEquals(aux, billList);
        verify(billService,times(1)).getListBillByDate("42231235","2020-06-16","2020-06-18");
    }
}