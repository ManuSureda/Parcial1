package com.utn.TPFinal.projections;

import com.fasterxml.jackson.annotation.JsonInclude;

public interface ClientProjection {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getName();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getLastName();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getDni();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getCity();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getFullNumber();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String getLineType();

    void setName(String name);
    void setLastName(String lastName);
    void setDni(String dni);
    void setCity(String city);
    void setFullNumber(String fullNumber);
    void setLineType(String lineType);
}
