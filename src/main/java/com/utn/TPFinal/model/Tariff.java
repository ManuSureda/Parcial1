package com.utn.TPFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "tariffs")
public class Tariff {
    @Id
    @GeneratedValue()
    @Column(name = "id_tariff")
    private Integer idTariff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @JsonBackReference
    @Fetch(FetchMode.JOIN)
    private City cityTariffOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @JsonBackReference
    @Fetch(FetchMode.JOIN)
    private City cityTariffDestination;

    @Column(name = "price_per_minute")
    private Long pricePerMinute;

    @Column(name = "cost_per_minute")
    private Long costPerMinute;

}
