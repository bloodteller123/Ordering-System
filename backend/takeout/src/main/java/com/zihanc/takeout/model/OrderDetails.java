package com.zihanc.takeout.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "order_details_table")
public class OrderDetails implements Serializable {
    @Serial
    private static final long serialVersionUID = 111L;
    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_sequence")
    private Long id;

    private String name;
    private String imgUrl;
//    private Long oderId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order_details")
    private Meal meal;
//    private Long mealId;
    private String flavours;
    private Integer number;
    private BigDecimal totals;
    private String notes;
}