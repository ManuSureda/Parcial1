package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.EmployeeDto;
import com.utn.TPFinal.dto.LoginRequestDto;
import com.utn.TPFinal.dto.UserPhoneDto;
import com.utn.TPFinal.dto.UserPhoneModifyDto;
import com.utn.TPFinal.exceptions.UserNotExistException;
import com.utn.TPFinal.model.User;
import com.utn.TPFinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /* 2) Manejo de Clientes (Service)*/

    // Alta de Cliente con su respectiva linea telefonica.
    public Integer addClientPhone(UserPhoneDto userPhone) throws JpaSystemException {
        return userRepository.addClientPhone(userPhone.getName(), userPhone.getLastName(), userPhone.getDni(), userPhone.getPassword(), userPhone.getCity(), userPhone.getLineType());
    }

    // Baja de Cliente con su respectiva linea telefonica.
    public void deleteClientPhone(String dni) throws JpaSystemException {
        userRepository.deleteClientPhone(dni);
    }

    public void suspendClient(String dni) throws JpaSystemException {
        userRepository.suspendClient(dni);
    }

    // Modificacion del Cliente y la linea telefonica.
    public void modifyClientPhone(UserPhoneModifyDto clientPhone) throws JpaSystemException {
        userRepository.modifyClientPhone(clientPhone.getUser(),
                clientPhone.getName(),
                clientPhone.getLastName(),
                clientPhone.getDni(),
                clientPhone.getPassword(),
                clientPhone.getCity(),
                clientPhone.getLineType());
    }


    public Integer addEmployee(EmployeeDto employee) throws JpaSystemException{
       return userRepository.addEmployee(employee.getName(), employee.getLastName(), employee.getDni(), employee.getPassword(), employee.getCity());
    }

    public User login(LoginRequestDto login) throws UserNotExistException {
        User user = userRepository.getByUsername(login.getDni(), login.getPassword());
        return Optional.ofNullable(user).orElseThrow(() -> new UserNotExistException("User not exist"));
    }


    public void reactiveClient(String dni) throws JpaSystemException{
        userRepository.reactiveClient(dni);
    }

    public User findClientByDni(String dni) {
        return userRepository.findClientByDni(dni);
    }

    public User getEmployeeDni(String dni) {
        return userRepository.findEmployeeByDni(dni);
    }

    public List<User> getAllClient() {
        return userRepository.getAllClient();
    }

    public List<User> getAllEmployee() {
        return userRepository.getAllEmployee();
    }
}
