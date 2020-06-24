package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.repository.TariffRepository;
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

class TariffServiceTest {

    private TariffService tariffService;
    private List<TariffProjection> list;
    private TariffProjection tariffProjection;

    @Mock
    private TariffRepository tariffRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        tariffService = new TariffService(tariffRepository);
        list = new ArrayList<>();

        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();
        this.tariffProjection = factory.createProjection(TariffProjection.class);

        tariffProjection.setIdTariff(1);
        tariffProjection.setCity_origin("Buenos Aires");
        tariffProjection.setCity_destination("Buenos Aires");
        tariffProjection.setPrice_per_minute((long) 3);
        tariffProjection.setCost_per_minute((float) 0.3);

        list.add(tariffProjection);
    }

    @Test
    void getAllTariffs() {
        when(tariffRepository.getAllTariffs()).thenReturn(list);
        List<TariffProjection> response = tariffService.getAllTariffs();

        assertNotNull(response);
        assertEquals(response,list);
        verify(tariffRepository,times(1)).getAllTariffs();
    }

    @Test
    void getTariffById() {
        when(tariffRepository.getTariffById(1)).thenReturn(tariffProjection);

        TariffProjection aux = tariffService.getTariffById(1);

        assertNotNull(aux);
        assertEquals(aux, tariffProjection);
        verify(tariffRepository,times(1)).getTariffById(1);
    }

    @Test
    void getTariffByName() {

        when(tariffRepository.getTariffByName("Buenos Aires","Buenos Aires")).thenReturn(tariffProjection);
        TariffProjection aux = tariffService.getTariffByName("Buenos Aires","Buenos Aires");

        assertNotNull(aux);
        assertEquals(aux,tariffProjection);
    }

    @Test
    void addTariff() {
        TariffDto dto = new TariffDto("Buenos Aires",
                "Buenos Aires",
                3,
                (float) 0.3);

        when(tariffRepository.addTariff(dto.getOriginCityName(),
                dto.getDestinationCityName(),
                dto.getPricePerMinute(),
                dto.getCostPerMinute())).thenReturn(1);

        Integer response = tariffService.addTariff(dto);
        assertEquals(response,1);
        verify(tariffRepository,times(1)).addTariff(dto.getOriginCityName(),
                dto.getDestinationCityName(),
                dto.getPricePerMinute(),
                dto.getCostPerMinute());
    }
}