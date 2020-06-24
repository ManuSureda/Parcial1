package com.utn.TPFinal.projections;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public interface BillProjection {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getComplete_name();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDni();
    String getFull_number();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer getCalls_quantity();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long getTotal_price();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long getTotal_cost();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date getBill_date();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date getExpiration_date();

    void setComplete_name(String name);
    void setDni(String dni);
    void setFull_number(String number);
    void setCalls_quantity(Integer quantity);
    void setTotal_price(long total_price);
    void setTotal_cost(long total_cost);
    void setBill_date(Date billDate);
    void setExpiration_date(Date expirationDate);

}

