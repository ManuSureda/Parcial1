package com.utn.TPFinal.repository;

import com.utn.TPFinal.model.Bill;
import com.utn.TPFinal.projections.BillProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query(value = "select * " + " from v_bills_info where full_number = ?1 and paid = 0", nativeQuery = true)
    List<BillProjection> getBillByNumber(String line);

    @Query(value = "select * " + " from v_bills_info where paid = 0", nativeQuery = true)
    List<BillProjection> getBillAll();

    /*Consulta de facturas por rango de fechas*/
    @Query(value ="select * from v_bills_info where dni = ? and DATE(bill_date) BETWEEN ? AND ?;", nativeQuery = true)
    List<BillProjection> getListBillByDate(String dni,String firstDate, String secondDate) ;

}
