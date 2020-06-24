package com.utn.TPFinal.controller.model;

import com.utn.TPFinal.projections.BillProjection;
import com.utn.TPFinal.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BillController {
    private final BillService billService;

    @Autowired
    public BillController(BillService billService) {
        this.billService = billService;
    }

    public List<BillProjection> getBillByNumber(String line)  {
        return billService.getBillByNumber(line);
    }

    public List<BillProjection> getBillAll()  {
        return billService.getBillAll();
    }

    public List<BillProjection> getBillDate(String dni, String first, String second)  {
        return billService.getListBillByDate(dni, first, second);
    }
}
