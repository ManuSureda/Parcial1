package com.utn.TPFinal.controller.advice;

import com.utn.TPFinal.dto.ErrorResponseDto;
import com.utn.TPFinal.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerAdviceTest {

    private ControllerAdvice controllerAdvice;

    @BeforeEach
    void setUp() {
        controllerAdvice = new ControllerAdvice();
    }

    @Test
    void testHandleLoginException() {
        InvalidLoginException e = new InvalidLoginException(new Throwable("Invalid login"));
        ErrorResponseDto responseDto = new ErrorResponseDto(2, "Invalid login");

        ErrorResponseDto ans = controllerAdvice.handleLoginException(e);

        assertEquals(responseDto,ans);
        assertEquals(responseDto.getDescription(),e.getMessage());
    }

    @Test
    void handleUserAllReadyExistException() {
        UserAllReadyExistException e = new UserAllReadyExistException("Incorrect user data");
        ErrorResponseDto responseDto = new ErrorResponseDto(3, "Incorrect user data");

        ErrorResponseDto ans = controllerAdvice.handleUserAllReadyExistException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handleUserNotExistException() {
        UserNotExistException e = new UserNotExistException("Incorrect user data");
        ErrorResponseDto responseDto = new ErrorResponseDto(4, "Incorrect user data");

        ErrorResponseDto ans = controllerAdvice.handleUserNotExistException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handleEmployeeException() {
        EmployeeException e = new EmployeeException("Incorrect user data");
        ErrorResponseDto responseDto = new ErrorResponseDto(5, "Incorrect user data");

        ErrorResponseDto ans = controllerAdvice.handleEmployeeException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handlePhoneLineException() {
        PhoneLineException e = new PhoneLineException("Incorrect phon line data");
        ErrorResponseDto responseDto = new ErrorResponseDto(6, "Incorrect phon line data");

        ErrorResponseDto ans = controllerAdvice.handlePhoneLineException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handleIncorrectDataCallException() {
        IncorrectDataCallException e = new IncorrectDataCallException("Incorrect call data");
        ErrorResponseDto responseDto = new ErrorResponseDto(7, "Incorrect call data");

        ErrorResponseDto ans = controllerAdvice.handleIncorrectDataCallException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handleIncorrectDataTariffException() {
        TariffException e = new TariffException("Incorrect tariff data");
        ErrorResponseDto responseDto = new ErrorResponseDto(8, "Incorrect tariff data");

        ErrorResponseDto ans = controllerAdvice.handleIncorrectDataTariffException(e);

        assertEquals(responseDto,ans);
    }

    @Test
    void handleSQLExeption() {
        SQLException e = new SQLException("Error interno en la base de datos");
        ErrorResponseDto responseDto = new ErrorResponseDto(9, "Error interno en la base de datos");

        ErrorResponseDto ans = controllerAdvice.handleSQLExeption(e);

        assertEquals(responseDto,ans);
    }
}