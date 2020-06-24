package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Procedure(procedureName = "sp_insert_employee")
    Integer addEmployee(@Param("pName") String name,
                     @Param("pLastName") String lastName,
                     @Param("pDni") String dni,
                     @Param("pUserPassword") String password,
                     @Param("pIdCity") Integer idCity) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_insert_client_and_phone_lines")
    Integer addClientPhone(@Param("pName") String name,
                        @Param("pLastName") String lastName,
                        @Param("pDni") String dni,
                        @Param("pUserPassword") String password,
                        @Param("pIdCity") Integer idCity,
                        @Param("pLineType") String lineType) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_delete_user")
    void deleteClientPhone(@Param("pDni") String dni) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_client_line_suspend")
    void suspendClient(@Param("pDni") String dni) throws JpaSystemException;

    @Transactional
    @Procedure(procedureName = "sp_modify_client")
    void modifyClientPhone(@Param("pIdUser") Integer idUser,
                           @Param("pName") String name,
                           @Param("pLastName") String lastName,
                           @Param("pDni") String dni,
                           @Param("pPassword") String password,
                           @Param("pIdCity") Integer idCity,
                           @Param("pLineType") String lineType) throws JpaSystemException;


    @Query(value = "SELECT * FROM users u WHERE u.dni = ? and u.user_password = ? and available = 1", nativeQuery = true)
    User getByUsername(@Param("dni") String dni, @Param("user_password") String password);

    @Transactional
    @Procedure(procedureName = "sp_client_reactive")
    void reactiveClient(@Param("pDni") String dni) throws JpaSystemException;

    @Query(value = "SELECT * FROM users u WHERE u.dni = ? and role_name = 'Employee' and available = 1 ", nativeQuery = true)
    User findEmployeeByDni(@Param("dni") String dni);

    @Query(value = "SELECT * FROM users u WHERE u.dni = ? and role_name = 'Client' and available = 1", nativeQuery = true)
    User findClientByDni(@Param("dni") String dni);

    @Query(value = "SELECT * FROM users u WHERE role_name = 'Employee' and available = 1", nativeQuery = true)
    List<User> getAllEmployee();

    @Query(value = "SELECT * FROM users u WHERE role_name = 'Client' and available = 1", nativeQuery = true)
    List<User> getAllClient();
}
