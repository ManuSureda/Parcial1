package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.ClientProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.BillService;
import com.utn.TPFinal.service.PhoneLineService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineControllerTest {

    private PhoneLineController phoneLineController;
    private ClientProjection clientProjection;

    @Mock
    private PhoneLineService phoneLineService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineController = new PhoneLineController(phoneLineService);
        ProjectionFactory factoryClient = new SpelAwareProxyProjectionFactory();
        clientProjection = factoryClient.createProjection(ClientProjection.class);
    }

    @Test
    void addPhoneLine() {
        PhoneLineByUserDto phoneLineByUserDto = new PhoneLineByUserDto(1,"Mobile");
        when(phoneLineService.addPhoneLine(phoneLineByUserDto)).thenReturn(1);
        Integer response = phoneLineController.addPhoneLine(phoneLineByUserDto);
        assertEquals(response,1);
        verify(phoneLineService,times(1)).addPhoneLine(phoneLineByUserDto);
    }

    @Test
    void deletePhoneLine() {
        doNothing().when(phoneLineService).deletePhoneLine(any());
        phoneLineController.deletePhoneLine(any());
        verify(phoneLineService,times(1)).deletePhoneLine(any());
    }

    @Test
    void enablePhoneLine() {
        doNothing().when(phoneLineService).enablePhoneLine(1);
        phoneLineController.enablePhoneLine(1);
        verify(phoneLineService,times(1)).enablePhoneLine(1);
    }

    @Test
    void getClientLine(){
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineService.getClientLine(clientProjection.getDni())).thenReturn(clientProjection);

        ClientProjection aux = phoneLineController.getClientLine(clientProjection.getDni());

        assertNotNull(aux);
        assertEquals(aux, clientProjection);
        verify(phoneLineService,times(1)).getClientLine(clientProjection.getDni());

    }
}