package com.utn.TPFinal.controller.login;

import com.utn.TPFinal.controller.backoffice.BillBackController;
import com.utn.TPFinal.controller.model.BillController;
import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.exceptions.InvalidLoginException;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.projections.CallsProjection;
import com.utn.TPFinal.session.SessionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import sun.rmi.runtime.Log;

import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class LoginControllerTest {
    LoginController loginController;

    @Mock
    SessionManager sessionManager;

    @Mock
    private UserController userController;

    @BeforeEach
    void setUp() {
        initMocks(this);
        loginController = new LoginController(userController, sessionManager);
    }

    @Test
    void loginOk() throws UserNotExistException, ValidationException, InvalidLoginException {
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        LoginRequestDto login = new LoginRequestDto("41686701", "santi");
        when(userController.login(login)).thenReturn(user);
        String token = sessionManager.createSession(user);

        ResponseEntity responseEntity = ResponseEntity.ok().headers(createHeaders(token)).build();
        ResponseEntity response = loginController.login(login);

        assertNotNull(response);
        assertEquals(responseEntity.getStatusCode(), response.getStatusCode());
    }

    @Test
    void loginBad() throws UserNotExistException, ValidationException, InvalidLoginException {
        LoginRequestDto loginRequestDto = new LoginRequestDto("41686701", "santi");
        when(userController.login(loginRequestDto)).thenThrow(new UserNotExistException("Incorrect user data"));

        assertThrows(InvalidLoginException.class, () -> {
            loginController.login(loginRequestDto);
        });

    }

    @Test
    void logoutOk() {
        User user = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        String token = sessionManager.createSession(user);
        doNothing().when(sessionManager).removeSession(token);
        ResponseEntity response = loginController.logout(token);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private HttpHeaders createHeaders(String token) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Authorization", token);
        return responseHeaders;
    }
}