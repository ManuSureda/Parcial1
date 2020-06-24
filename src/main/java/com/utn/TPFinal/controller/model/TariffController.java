package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TariffController {
    private final TariffService tariffService;

    @Autowired
    public TariffController(TariffService tariffService) {
        this.tariffService = tariffService;
    }


    public List<TariffProjection> getAllTariffs() {
        return tariffService.getAllTariffs();
    }

    public TariffProjection getTariffById(Integer idTariff)  {
        return tariffService.getTariffById(idTariff);
    }

    public TariffProjection getTariffByName(String originCityName, String destinationCityName)  {
        return tariffService.getTariffByName(originCityName,destinationCityName);
    }

    public Integer addTariff(TariffDto tariffDto) {
       return tariffService.addTariff(tariffDto);
    }
}
