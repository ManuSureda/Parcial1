package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class TariffControllerTest {

    private TariffController tariffController;
    private TariffProjection tariffProjection;

    @Mock
    private TariffService tariffService;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffController = new TariffController(tariffService);
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.tariffProjection = factory.createProjection(TariffProjection.class);

        tariffProjection.setIdTariff(1);
        tariffProjection.setCity_origin("Buenos Aires");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);
    }

    @Test
    void getAllTariffsOk() {

        List<TariffProjection> tarifs = new ArrayList<>();
        tarifs.add(tariffProjection);

        when(tariffService.getAllTariffs()).thenReturn(tarifs);

        List<TariffProjection> tarifsAux = tariffController.getAllTariffs();

        assertNotNull(tarifsAux);
        assertEquals(tarifs, tarifsAux);
        verify(tariffService,times(1)).getAllTariffs();
    }

    @Test
    void getTariffByIdOk() {

        when(tariffService.getTariffById(1)).thenReturn(tariffProjection);

        TariffProjection aux = tariffController.getTariffById(1);

        assertNotNull(aux);
        assertEquals(aux, tariffProjection);
        verify(tariffService,times(1)).getTariffById(1);
    }

    @Test
    void getTariffByNameOk() {

        when(tariffController.getTariffByName("Buenos Aires","Buenos Aires")).thenReturn(tariffProjection);
        TariffProjection aux = tariffService.getTariffByName("Buenos Aires","Buenos Aires");

        assertNotNull(aux);
        assertEquals(aux,tariffProjection);
    }

    @Test
    void addTariffOk() {
        when(tariffService.addTariff(new TariffDto())).thenReturn(1);
        Integer response = tariffController.addTariff(new TariffDto());
        assertEquals(response,1);
        verify(tariffService,times(1)).addTariff(new TariffDto());
    }
}