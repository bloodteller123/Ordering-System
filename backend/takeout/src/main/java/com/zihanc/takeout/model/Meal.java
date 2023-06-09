package com.zihanc.takeout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "meal_table")
@EqualsAndHashCode(exclude = {"category", "cart", "employee", "order_details"})
public class Meal implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;

    @Id
    @SequenceGenerator(
            name = "id_sequence",
            sequenceName = "id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "id_sequence")
    private Long id;

    private String name;
//    @Column(nullable = false)
//    private Long categoryId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoryId", referencedColumnName = "id")
//    @JsonIgnoreProperties("meals")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    // the fk is stored as 'categoryId', which is the primary key from 'Category'
    private Category category;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "cart_id", referencedColumnName = "id")
//    private ShoppingCart cart;

    private BigDecimal price;
    //item code
    private String code;

    private String imgUrl;
    private String description;

    // 0 unavailable 1 available
    private Integer status;

//    private LocalDateTime createTime;
//    private LocalDateTime updateTime;

//    private Long creatorId;
    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_details",referencedColumnName = "id")
    private OrderDetails order_details;

}
