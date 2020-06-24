package com.utn.TPFinal.service;

import ch.qos.logback.core.net.server.Client;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.projections.ClientProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.repository.PhoneLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineServiceTest {

    private PhoneLineService phoneLineService;
    private ClientProjection clientProjection;

    @Mock
    private PhoneLineRepository phoneLineRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineService= new PhoneLineService(phoneLineRepository);
        ProjectionFactory factoryClient = new SpelAwareProxyProjectionFactory();
        clientProjection = factoryClient.createProjection(ClientProjection.class);
    }

    @Test
    void addPhoneLine() {
        PhoneLineByUserDto phoneLine = new PhoneLineByUserDto(1,"mobile");
        when(phoneLineRepository.addPhoneLine(phoneLine.getUser(),phoneLine.getLineType())).thenReturn(1);
        Integer response = phoneLineService.addPhoneLine(phoneLine);

        assertNotNull(response);
        assertEquals(1,response);
        verify(phoneLineRepository,times(1)).addPhoneLine(phoneLine.getUser(),phoneLine.getLineType());
    }

    @Test
    void deletePhoneLine() {
        doNothing().when(phoneLineRepository).deletePhoneLine(any());
        phoneLineService.deletePhoneLine(any());
        verify(phoneLineRepository).deletePhoneLine(any());
    }

    @Test
    void enablePhoneLine() {
        doNothing().when(phoneLineRepository).enablePhoneLine(any());
        phoneLineService.enablePhoneLine(any());
        verify(phoneLineRepository).enablePhoneLine(any());
    }

    @Test
    void getClientLine(){
        clientProjection.setName("Santiago");
        clientProjection.setLastName("Labatut");
        clientProjection.setDni("41686701");
        clientProjection.setCity("Mar del Plata");
        clientProjection.setFullNumber("02235351545");
        clientProjection.setLineType("Home");

        when(phoneLineRepository.getClientLine(clientProjection.getDni())).thenReturn(clientProjection);

        ClientProjection aux = phoneLineService.getClientLine(clientProjection.getDni());

        assertNotNull(aux);
        assertEquals(aux, clientProjection);
        verify(phoneLineRepository,times(1)).getClientLine(clientProjection.getDni());

    }
}