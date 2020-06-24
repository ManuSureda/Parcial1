package com.utn.TPFinal.projections;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;

public interface CallsProjection {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getFull_name_o();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDni_user_origin();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getOrigin_line();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getOrigin_province();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getOrigin_city();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getFull_name_d();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDni_user_destination();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDestination_line();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDestination_city();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long getDuration();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer getTotal_price();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Float getTotal_cost();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Date getCall_date();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Integer getCant();

    void setFull_name_o(String Full_name_o);
    void setDni_user_origin(String Dni_user_origin);
    void setOrigin_line(String originLine);
    void setOrigin_province(String Origin_province);
    void setOrigin_city(String originCity);
    void setFull_name_d(String Full_name_d);
    void setDni_user_destination(String Dni_user_destination);
    void setDestination_line(String destinationLine);
    void setDestination_city(String destination_city);
    void setDuration(Long duration);
    void setTotal_price(Integer totalPrice);
    void setTotal_cost(Float totalCost);
    void setCall_date(Date callDate);
    void setCant(int cant);
}