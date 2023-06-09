package com.zihanc.takeout.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user_table")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String phone;
    private String avatarUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    Set<Order> orders = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ShoppingCart cart;
}
