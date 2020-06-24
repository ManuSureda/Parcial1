package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.exceptions.PhoneLineException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/backoffice/phoneline")
public class PhoneLineBackController {
    private final PhoneLineController phoneLineController;

    @Autowired
    public PhoneLineBackController(PhoneLineController phoneLineController) {
        this.phoneLineController = phoneLineController;
    }

    // Alta de Linea con su respectivo usuario.
    @PostMapping("/")
    public ResponseEntity addPhoneLine(@RequestBody PhoneLineByUserDto phoneLine) throws PhoneLineException, URISyntaxException {
        try {
            Integer idLine = phoneLineController.addPhoneLine(phoneLine);
            return ResponseEntity.created(new URI("http://localhost:8080/backoffice/phoneline/" + idLine)).body(phoneLine);
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

    // Baja de Linea con su respectivo usuario.
    @DeleteMapping("/{idLine}")
    public ResponseEntity deletePhoneLine(@PathVariable Integer idLine) throws PhoneLineException {
        try {
            phoneLineController.deletePhoneLine(idLine);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }

    // Activar una Linea con su respectivo usuario.
    @PostMapping("/{idLine}")
    public ResponseEntity enablePhoneLine(@PathVariable Integer idLine) throws PhoneLineException {
        try {
            phoneLineController.enablePhoneLine(idLine);
            return ResponseEntity.ok().build();
        } catch (JpaSystemException e) {
            throw new PhoneLineException(e.getCause().getCause().getMessage());
        }
    }
}
