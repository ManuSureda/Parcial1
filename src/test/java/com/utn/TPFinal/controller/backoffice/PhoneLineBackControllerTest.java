package com.utn.TPFinal.controller.backoffice;

import com.utn.TPFinal.controller.model.PhoneLineController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.exceptions.PhoneLineException;
import com.utn.TPFinal.exceptions.UserAllReadyExistException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineBackControllerTest {
    private PhoneLineBackController phoneLineBackController;

    @Mock
    private PhoneLineController phoneLineController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineBackController = new PhoneLineBackController(phoneLineController);
    }

    @Test
    void addPhoneLine() throws URISyntaxException, PhoneLineException {
        PhoneLineByUserDto phoneLineByUser = new PhoneLineByUserDto( 3, "Home");
        Integer idPhoneLineByUser = 1;
        when(phoneLineController.addPhoneLine(phoneLineByUser)).thenReturn(idPhoneLineByUser);
        ResponseEntity responseEntity = ResponseEntity.created(new URI("http://localhost:8080/backoffice/phoneline/" + idPhoneLineByUser)).body(phoneLineByUser);

        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(responseEntity, phoneLineBackController.addPhoneLine(phoneLineByUser));
        verify(phoneLineController, times(1)).addPhoneLine(phoneLineByUser);
    }

    @Test()
    void addPhoneLineException() throws JpaSystemException {
        PhoneLineByUserDto phoneLineByUser = new PhoneLineByUserDto( 3, "Home");

        when(phoneLineController.addPhoneLine(phoneLineByUser)).thenThrow(new JpaSystemException(new RuntimeException(new SQLException())));

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.addPhoneLine(phoneLineByUser);
        });

    }

    @Test
    void deletePhoneLine() throws PhoneLineException {
        doNothing().when(phoneLineController).deletePhoneLine(1);
        ResponseEntity responseEntity = phoneLineBackController.deletePhoneLine(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(phoneLineController, times(1)).deletePhoneLine(1);
    }

    @Test()
    void deleteClientException() throws JpaSystemException{
        Integer idLine = 1;
        doThrow(new JpaSystemException(new RuntimeException(new SQLException()))).when(phoneLineController).deletePhoneLine(idLine);

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.deletePhoneLine(idLine);
        });

    }


    @Test
    void enablePhoneLine() throws PhoneLineException {
        doNothing().when(phoneLineController).enablePhoneLine(1);
        ResponseEntity responseEntity = phoneLineBackController.enablePhoneLine(1);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(phoneLineController, times(1)).enablePhoneLine(1);
    }

    @Test()
    void enableClientException() throws JpaSystemException{
        Integer idLine = 1;
        doThrow(new JpaSystemException(new RuntimeException(new SQLException()))).when(phoneLineController).enablePhoneLine(idLine);

        assertThrows(PhoneLineException.class, () -> {
            phoneLineBackController.enablePhoneLine(idLine);
        });

    }
}