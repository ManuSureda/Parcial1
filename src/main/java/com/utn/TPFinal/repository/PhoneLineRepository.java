package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.PhoneLine;
import com.utn.TPFinal.projections.ClientProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneLineRepository extends JpaRepository<PhoneLine, Integer> {
    @Procedure(procedureName = "sp_phone_lines")
    Integer addPhoneLine(@Param("pIdUser") Integer idUser,
                      @Param("pLineType") String TypeLine) throws JpaSystemException;

    @Procedure(procedureName = "sp_line_suspend")
    void deletePhoneLine(@Param("pIdLine") Integer idLine);

    @Procedure(procedureName = "sp_line_active")
    void enablePhoneLine(@Param("pIdLine") Integer idLine);
    @Query(value ="select name, last_name as lastname, dni, city, full_number as fullnumber, line_type as linetype from v_client_info where full_number = ?", nativeQuery = true)
    ClientProjection getClientLine(String line);
}
