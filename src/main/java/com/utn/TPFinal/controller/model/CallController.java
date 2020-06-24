package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.CallDto;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class CallController {
    private final CallService callService;

    @Autowired
    public CallController(CallService callService) {
        this.callService = callService;
    }

    public List<CallsProjection> getCall(String dni) {
        return callService.getListCall(dni);
    }

    public Integer addCall(CallDto callDto)  {
        return callService.addCall(callDto);
    }

    public List<CallsProjection> getTopTenDestinations(String dni) {
        return callService.getTopTenDestinations(dni);
    }

    public List<CallsProjection> getCallByDate(String dni, String firstDate, String secondDate) {
        return callService.getListCallByDate(dni,firstDate, secondDate);
    }
}
