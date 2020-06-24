package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.projections.TariffProjection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class TariffBackControllerTest {

    private TariffBackController tariffBackController;
    private TariffProjection tariffProjection;

    @Mock
    private TariffController tariffController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffBackController = new TariffBackController(tariffController);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        tariffProjection = factory.createProjection(TariffProjection.class);
    }

    @Test
    void getAllTariffsOk() {
        tariffProjection.setCity_origin("Mar del Plata");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setCost_per_minute((float) 30);
        tariffProjection.setPrice_per_minute((long) 400);

        List<TariffProjection> tariffList = new ArrayList<TariffProjection>();
        tariffList.add(tariffProjection);

        when(tariffController.getAllTariffs()).thenReturn(tariffList);
        ResponseEntity<List<TariffProjection>> response = tariffBackController.getAllTariffs();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(tariffList, response.getBody());
    }

    @Test
    void getAllTariffsEmpty() {
        List<TariffProjection> tariffList = new ArrayList<TariffProjection>();

        when(tariffController.getAllTariffs()).thenReturn(tariffList);
        ResponseEntity<List<TariffProjection>> response = tariffBackController.getAllTariffs();

        assertEquals(204, response.getStatusCodeValue());
    }

    @Test
    void getTariffByIdOk() {
        tariffProjection.setIdTariff(1);
        tariffProjection.setCity_origin("Mar del Plata");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);

        when(tariffController.getTariffById(1)).thenReturn(tariffProjection);

        ResponseEntity response = tariffBackController.getTariffById(1);

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(tariffProjection), response);
    }

    @Test
    void getTariffByIdBad() {
        when(tariffController.getTariffById(800)).thenReturn(null);

        ResponseEntity responseController = tariffBackController.getTariffById(800);
        assertEquals(HttpStatus.NOT_FOUND, responseController.getStatusCode());
    }


    @Test
    void getTariffByName() {
        tariffProjection.setIdTariff(1);
        tariffProjection.setCity_origin("Mar del Plata");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);

        when(tariffController.getTariffByName("Mar del Plata", "Buenos Aires")).thenReturn(tariffProjection);

        ResponseEntity response = tariffBackController.getTariffByName("Mar del Plata", "Buenos Aires");

        assertNotNull(response);
        assertEquals(ResponseEntity.ok(tariffProjection), response);
    }

    @Test
    void getTariffByNameBad() {
        when(tariffController.getTariffByName("Argentina", "Buenos Aires")).thenReturn(null);

        ResponseEntity responseController = tariffBackController.getTariffByName("Argentina", "Buenos Aires");
        assertEquals(HttpStatus.NOT_FOUND, responseController.getStatusCode());
    }

}