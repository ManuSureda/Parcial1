package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.TariffController;
import com.utn.TPFinal.projections.TariffProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/backoffice/tariff")
public class TariffBackController {
    private final TariffController tariffController;

    @Autowired
    public TariffBackController(TariffController tariffController) {
        this.tariffController = tariffController;
    }

    //Consulta de todas las tarifas
    @GetMapping("/")
    public ResponseEntity<List<TariffProjection>> getAllTariffs() {
        List<TariffProjection> tariffs = tariffController.getAllTariffs();
        if (tariffs.size() > 0) {
            return ResponseEntity.ok().body(tariffs);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    //Consulta de tarifa por id
    @GetMapping("/{idTariff}")
    public ResponseEntity getTariffById(@PathVariable Integer idTariff) {
        TariffProjection tariffById = tariffController.getTariffById(idTariff);
        if(tariffById == null){
            return ResponseEntity.notFound().build();
        } else{
           return ResponseEntity.ok(tariffById);
        }
    }

    //Consulta de tarifa por ciudad de origen y ciudad de destino
    @GetMapping("/city")
    public ResponseEntity getTariffByName(@RequestParam String originCityName, @RequestParam String destinationCityName){
        TariffProjection tariffByName = tariffController.getTariffByName(originCityName, destinationCityName);
        if(tariffByName == null){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(tariffByName);
        }
    }
}
