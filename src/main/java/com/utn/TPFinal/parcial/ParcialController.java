package com.utn.TPFinal.parcial;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.projections.CallsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parcial")
public class ParcialController {

    private final CallController callController;

    @Autowired
    public ParcialController(CallController callController) {
        this.callController = callController;
    }

    @GetMapping("/top")
    public ResponseEntity<List<CallsProjection>> getTopTenDestinations(@RequestParam String dni) {

        List<CallsProjection> callDestination = callController.getTopTenDestinations(dni);
        if (callDestination.size() > 0) {
            return ResponseEntity.ok().body(callDestination);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
