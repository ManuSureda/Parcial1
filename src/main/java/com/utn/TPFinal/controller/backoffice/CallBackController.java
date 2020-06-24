package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.CallController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.CallsProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/backoffice/call")
public class CallBackController {
    private final CallController callController;
    private final UserController userController;

    @Autowired
    public CallBackController(CallController callController, UserController userController) {
        this.callController = callController;
        this.userController = userController;
    }

    //Consulta de llamadas por dni
    @GetMapping("/") // localhost:8080/call/dni?=4123
    public ResponseEntity<List<CallsProjection>> getCall(@RequestParam String dni) {
        User client = userController.getClient(dni);
        if(client == null){
            return ResponseEntity.notFound().build();
        }else{
            List<CallsProjection> calls = callController.getCall(dni);
            if (calls.size() > 0) {
                return ResponseEntity.ok().body(calls);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }

    }


}