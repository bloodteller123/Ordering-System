package com.zihanc.takeout.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "cart_item")
public class CartItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ShoppingCart cart;

    @ManyToOne
    @JoinColumn(name = "meal_id", referencedColumnName = "id")
    private Meal meal;

    private int quantity;
    private BigDecimal price;
}
