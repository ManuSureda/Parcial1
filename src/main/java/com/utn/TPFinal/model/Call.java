package com.utn.TPFinal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "calls")
public class Call {
    @Id
    @GeneratedValue()
    @Column(name = "id_call")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idCall;

    @Column(name = "call_date")
    private Date callDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_origin")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
   private PhoneLine phoneLineOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_line_destination")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
   private PhoneLine phoneLineDestination;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "total_cost")
    private Integer totalCost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_origin")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
   private City cityOrigin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_city_destination")
    @Fetch(FetchMode.JOIN)
    @JsonBackReference
   private City cityDestination;

    @Column(name = "billed")
    @JsonIgnore
    private Boolean billed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bill")
    @JsonBackReference
    private Bill bill;




}
