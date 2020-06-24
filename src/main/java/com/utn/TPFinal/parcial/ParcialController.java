package com.utn.TPFinal.parcial;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.projections.CallsProjection;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "returns the 10 destinations most called by the specified dni")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "List of most called destination"),
            @ApiResponse(code = 204,message = "empty call list")
    })
    public ResponseEntity<List<CallsProjection>> getTopTenDestinations(@RequestParam String dni) {

        List<CallsProjection> callDestination = callController.getTopTenDestinations(dni);
        if (callDestination.size() > 0) {
            return ResponseEntity.ok().body(callDestination);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
