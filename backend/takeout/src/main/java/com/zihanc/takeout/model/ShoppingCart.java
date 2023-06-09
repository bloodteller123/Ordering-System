package com.zihanc.takeout.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "cart_table")
public class ShoppingCart implements Serializable {
    @Serial
    private static final long serialVersionUID = 888L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    // name of the meal
//    private String name;
//    private String imgUrl;
//    //TODO
//    private Long userId;
    //https://www.baeldung.com/jpa-one-to-one
    @OneToOne(mappedBy = "cart")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cart", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @JsonIgnoreProperties("cart")
    private Set<CartItem> cartItems = new HashSet<>();
    public void addCart(CartItem c){
        cartItems.add(c);
    }
    public void removeCart(CartItem c){
        cartItems.remove(c);
    }
//    private LocalDateTime createTime;
}
