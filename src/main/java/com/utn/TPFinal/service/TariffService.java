package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.TariffDto;
import com.utn.TPFinal.projections.TariffProjection;
import com.utn.TPFinal.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffService {
    private final TariffRepository tariffRepository;

    @Autowired
    public TariffService(TariffRepository tariffRepository) {
        this.tariffRepository = tariffRepository;
    }

    public List<TariffProjection> getAllTariffs() {
        return tariffRepository.getAllTariffs();
    }

    public TariffProjection getTariffById(Integer idTariff) {
        return tariffRepository.getTariffById(idTariff);
    }

    public TariffProjection getTariffByName(String originCityName, String destinationCityName) {
        return tariffRepository.getTariffByName(originCityName,destinationCityName);
    }

    public Integer addTariff(TariffDto tariffDto) {
        return tariffRepository.addTariff(tariffDto.getOriginCityName(),tariffDto.getDestinationCityName(),tariffDto.getPricePerMinute(),tariffDto.getCostPerMinute());
    }
}
