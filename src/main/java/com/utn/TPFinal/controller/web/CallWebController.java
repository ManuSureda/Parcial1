package com.utn.TPFinal.controller.web;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.session.SessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/web/call")
public class CallWebController {
    private final SessionManager sessionManager;
    private final CallController callController;

    @Autowired
    public CallWebController(SessionManager sessionManager, CallController callController) {
        this.sessionManager = sessionManager;
        this.callController = callController;
    }

    //Consulta del top 10 de destinos mas llamados del usuario logeado
    @GetMapping("/destination")
    public ResponseEntity<List<CallsProjection>> getTopTenDestinations(@RequestHeader("Authorization") String sessionToken) {
        User session = sessionManager.getCurrentUser(sessionToken);
        List<CallsProjection> callDestination = callController.getTopTenDestinations(session.getDni());
        if (callDestination.size() > 0) {
            return ResponseEntity.ok().body(callDestination);
        } else {
            return ResponseEntity.noContent().build();
        }
    }



    //Consulta del llamadas por rango de fecha del usuario logeado
    @GetMapping("/date") // localhost:8080/call/date?first=31-05-2019
    public ResponseEntity<List<CallsProjection>> getCallByDate(@RequestHeader("Authorization") String sessionToken, @RequestParam String first, @RequestParam String second) {
        if (first.compareTo(second) > 0) {
//            ErrorResponseDto error = new ErrorResponseDto(10, "The first date cannot be greater than the second");
            return ResponseEntity.badRequest().build();
        } else {
            User session = sessionManager.getCurrentUser(sessionToken);
            List<CallsProjection> callByDate = callController.getCallByDate(session.getDni(), first, second);
            if (callByDate.size() > 0) {
                return ResponseEntity.ok().body(callByDate);
            } else {
                return ResponseEntity.noContent().build();
            }
        }
    }


}
