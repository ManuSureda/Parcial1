package com.utn.TPFinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TariffDto {
    String originCityName;
    String destinationCityName;
    long pricePerMinute;
    float costPerMinute;
}