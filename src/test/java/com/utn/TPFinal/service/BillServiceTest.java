package com.utn.TPFinal.service;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.repository.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class BillServiceTest {

    private BillService billService;

    private BillProjection billProjection;
    private List<BillProjection> billList;


    @Mock
    private BillRepository billRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        billService = new BillService(billRepository);

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
    void getBillByNumber() {
        when(billRepository.getBillByNumber("022351651")).thenReturn(billList);

        List<BillProjection> response = new ArrayList<>();
        response = billService.getBillByNumber("022351651");

        assertNotNull(response);
        assertEquals(response,billList);
        verify(billRepository,times(1)).getBillByNumber("022351651");
    }

    @Test
    void getBillAll() {
        when(billRepository.getBillAll()).thenReturn(billList);

        List<BillProjection> response = new ArrayList<>();
        response = billService.getBillAll();

        assertNotNull(response);
        assertEquals(response,billList);
        verify(billRepository,times(1)).getBillAll();
    }

    @Test
    void getListBillByDate() {
        when(billRepository.getListBillByDate("123","2020-07-05","2020-01-05")).thenReturn(billList);

        List<BillProjection> response = new ArrayList<>();
        response = billService.getListBillByDate("123","2020-07-05","2020-01-05");

        assertNotNull(response);
        assertEquals(response,billList);
        verify(billRepository,times(1)).getListBillByDate("123","2020-07-05","2020-01-05");
    }
}