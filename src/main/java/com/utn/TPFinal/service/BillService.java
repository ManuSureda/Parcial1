package com.utn.TPFinal.service;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.repository.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<BillProjection> getBillByNumber(String line){
        return billRepository.getBillByNumber(line);
    }

    public List<BillProjection> getBillAll(){
        return billRepository.getBillAll();
    }

    public List<BillProjection> getListBillByDate(String dni, String firstDate, String secondDate){
        return billRepository.getListBillByDate(dni,firstDate, secondDate);
    }


}
