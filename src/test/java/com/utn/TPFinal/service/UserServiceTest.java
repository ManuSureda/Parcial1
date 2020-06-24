package com.utn.TPFinal.service;

import com.utn.TPFinal.controller.model.UserController;
import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.exceptions.ValidationException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

import static com.utn.TPFinal.model.UserType.Admin;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    void getAllEmployee() {
        List<User> list = new ArrayList<>();
        User user = new User(1, "Santiago",
                "Labatut", "41686701",
                "admin123", 1, null,
                null, null);

        list.add(user);

        when(userRepository.getAllEmployee()).thenReturn(list);
        List<User> aux = new ArrayList<>();
        aux = userService.getAllEmployee();
        assertNotNull(aux);
        assertEquals(aux, list);
        verify(userRepository, times(1)).getAllEmployee();
    }

    @Test
    void getAllClient() {
        List<User> list = new ArrayList<>();
        User a = new User();

        list.add(a);

        when(userRepository.getAllClient()).thenReturn(list);
        List<User> aux = new ArrayList<>();
        aux = userService.getAllClient();
        assertNotNull(aux);
        assertEquals(aux, list);
        verify(userRepository, times(1)).getAllClient();

    }

    @Test
    void findClientByDni() {
        User employee = new User();
        when(userRepository.findClientByDni("41686701")).thenReturn(employee);

        User aux = userService.findClientByDni("41686701");
        assertNotNull(aux);
        assertEquals(aux, employee);
        verify(userRepository, times(1)).findClientByDni("41686701");
    }

    @Test
    void getEmployeeDni() {
        User client = new User();
        when(userRepository.findEmployeeByDni("41686701")).thenReturn(client);

        User aux = userService.getEmployeeDni("41686701");
        assertNotNull(aux);
        assertEquals(aux, client);
        verify(userRepository, times(1)).findEmployeeByDni("41686701");

    }

    @Test
    void addClientPhone() {
        UserPhoneDto dto = new UserPhoneDto("Leunam", "juanjo", "123", "321", 1, "Mobile");

        when(userRepository.addClientPhone(dto.getName(),
                dto.getLastName(),
                dto.getDni(),
                dto.getPassword(),
                dto.getCity(),
                dto.getLineType())).thenReturn(1);
        Integer response = userService.addClientPhone(dto);
        assertEquals(response, 1);
        verify(userRepository, times(1)).addClientPhone(dto.getName(),
                dto.getLastName(),
                dto.getDni(),
                dto.getPassword(),
                dto.getCity(),
                dto.getLineType());
    }

    @Test
    void deleteClientPhone() {
        doNothing().when(userRepository).deleteClientPhone(any());
        userService.deleteClientPhone(any());
        verify(userRepository, times(1)).deleteClientPhone(any());
    }

    @Test
    void suspendClient() {
        doNothing().when(userRepository).suspendClient("123");
        userService.suspendClient("123");
        verify(userRepository, times(1)).suspendClient("123");
    }

    @Test
    void modifyClientPhone() {
        UserPhoneModifyDto clientPhone = new UserPhoneModifyDto(1, "manu", "sure", "123", "321", 1, "Mobile");
        doNothing().when(userRepository).modifyClientPhone(clientPhone.getUser(),
                clientPhone.getName(),
                clientPhone.getLastName(),
                clientPhone.getDni(),
                clientPhone.getPassword(),
                clientPhone.getCity(),
                clientPhone.getLineType());

        userService.modifyClientPhone(clientPhone);

        verify(userRepository, times(1)).modifyClientPhone(clientPhone.getUser(),
                clientPhone.getName(),
                clientPhone.getLastName(),
                clientPhone.getDni(),
                clientPhone.getPassword(),
                clientPhone.getCity(),
                clientPhone.getLineType());
    }

    @Test
    void addEmployee() {
        EmployeeDto employee = new EmployeeDto("manu", "sure", "321", "321", 1);
        when(userRepository.addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), employee.getCity())).thenReturn(1);
        Integer response = userService.addEmployee(employee);
        assertEquals(response, 1);
        verify(userRepository, times(1)).addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), employee.getCity());
    }


    @Test
    void reactiveClient() {
        doNothing().when(userRepository).reactiveClient("123");
        userService.reactiveClient("123");
        verify(userRepository, times(1)).reactiveClient("123");
    }

    @Test
    void loginOk() throws UserNotExistException, ValidationException {
        User loggedUser = new User(1, "Santiago", "Labatut", "41686701", "santi", 1, null, Admin, null);
        LoginRequestDto login = new LoginRequestDto("41686701", "santi");
        //Cuando llame al mock service.login devuelvo el logged user
        when(userRepository.getByUsername(login.getDni(),login.getPassword())).thenReturn(loggedUser);

        User returnedUser = userService.login(login);

        //Hacemos los assert
        assertEquals(loggedUser.getDni(), returnedUser.getDni());
        assertEquals(loggedUser.getUserPassword(), returnedUser.getUserPassword());
        verify(userRepository, times(1)).getByUsername(login.getDni(),login.getPassword());
    }


    @Test
    public void testLoginUserNotFound() throws UserNotExistException, ValidationException {
//        LoginRequestDto loginRequestDto = new LoginRequestDto("41686701", "santi");
//        when(userRepository.getByUsername(null,null)).thenThrow(new UserNotExistException("Incorrect User Data"));
//        assertThrows(UserNotExistException.class, () -> {
//            userService.login(null);
//        });
    }


//    @Test
//    public void testLoginUserNotFound() throws UserNotExistException, ValidationException {
//        LoginRequestDto loginRequestDto = new LoginRequestDto("41686701", "santi");
//        when(userRepository.getByUsername(loginRequestDto.getDni(),loginRequestDto.getPassword())).thenThrow(new UserNotExistException("Incorrect user data"));
//        assertThrows(UserNotExistException.class, () -> {
//            userService.login(loginRequestDto);
//        });
//    }

//    @Test
//    public void testLoginInvalidData() throws UserNotExistException, ValidationException {
//        assertThrows(ValidationException.class, () -> {
//            userService.login(new LoginRequestDto(null, "bla"));
//        });
//    }
}