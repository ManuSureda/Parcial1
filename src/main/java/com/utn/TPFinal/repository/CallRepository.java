package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.Call;
import com.utn.TPFinal.projections.CallsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CallRepository extends JpaRepository<Call,Integer> {

    @Query(value = "select *" + " from v_calls_info where dni_user_origin = ?1", nativeQuery = true)
    List<CallsProjection> getListCall(String dni);

    @Procedure(procedureName = "sp_insert_call")
    Integer addCall(@Param("pLineOrigin") String lineOrigin,
                 @Param("pLineDestination") String lineDestination,
                 @Param("pDuration") Long duration,
                 @Param("pCallDate") Date callDate) throws JpaSystemException;

    /*Consulta de TOP 10 destinos más llamados por el usuario.*/
    @Query(value = "select full_name_o, destination_city, count(destination_city) as cant from v_calls_info where dni_user_origin = ?1 group by destination_city order by cant desc limit 10;", nativeQuery = true)
    List<CallsProjection> getTopTenDestinations(String dni);

    /*Consulta de llamadas por rango de fechas*/
    @Query(value ="select * from v_calls_info where dni_user_origin = ? and DATE(call_date) BETWEEN ? AND ?;", nativeQuery = true)
    List<CallsProjection> getListCallByDate(String dni, String firstDate, String secondDate);

}
