package com.utn.TPFinal.projections;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface TariffProjection {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getCity_origin();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getCity_destination();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Long getPrice_per_minute();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Float getCost_per_minute();

    void setCity_origin(String cityOrigin);
    void setCity_destination(String cityDestination);
    void setPrice_per_minute(Long pricePerMinute);
    void setCost_per_minute(Float costPerMinute);

    void setIdTariff(int id);
}
