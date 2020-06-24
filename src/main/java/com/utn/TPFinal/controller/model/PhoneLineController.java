package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.projections.ClientProjection;
import com.utn.TPFinal.service.PhoneLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Controller;

@Controller
public class PhoneLineController {
    private final PhoneLineService phoneLineService;

    @Autowired
    public PhoneLineController(PhoneLineService phoneLineService) {
        this.phoneLineService = phoneLineService;
    }

    public Integer addPhoneLine(PhoneLineByUserDto phoneLine) throws JpaSystemException {
       return phoneLineService.addPhoneLine(phoneLine);
    }

    public void deletePhoneLine(Integer idLine) throws JpaSystemException {
        phoneLineService.deletePhoneLine(idLine);
    }

    public void enablePhoneLine(Integer idLine) throws JpaSystemException {
        phoneLineService.enablePhoneLine(idLine);
    }

    public ClientProjection getClientLine(String line) {
        return phoneLineService.getClientLine(line);
    }
}
