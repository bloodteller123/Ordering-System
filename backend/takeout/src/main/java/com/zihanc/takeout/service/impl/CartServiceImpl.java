package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.CartItem;
import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.ShoppingCart;
import com.zihanc.takeout.model.User;
import com.zihanc.takeout.repository.CartItemRepo;
import com.zihanc.takeout.repository.CartRepo;
import com.zihanc.takeout.service.CartService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    private final CartRepo cartRepo;
    private final CartItemRepo cartItemRepo;
    @Autowired
    public CartServiceImpl(CartRepo cartRepo, CartItemRepo cartItemRepo) {
        this.cartRepo = cartRepo;
        this.cartItemRepo = cartItemRepo;
    }

    @Override
    public ShoppingCart findByUser(User user) {
        return cartRepo.findByUser(user).orElse(null);
    }

    @Override
    public ShoppingCart save(ShoppingCart cart) {
        log.info(cart.toString());
        return cartRepo.save(cart);
    }

    @Transactional
    @Override
    public ShoppingCart updateCart(User user, Meal meal, int quanyity) {
        Optional<ShoppingCart> cart_op = cartRepo.findByUser(user);
        ShoppingCart cart = cart_op.map(c -> {
            CartItem ci = findAndUpdate(c,meal, quanyity);

            c.getCartItems().add(ci);
            return c;
        }).orElse(null);
        if(cart==null){
            cart = new ShoppingCart();
            cart.setUser(user);
            CartItem ci = new CartItem();

            ci.setMeal(meal);
            ci.setQuantity(quanyity);
            ci.setPrice(BigDecimal.valueOf(ci.getQuantity()).multiply(meal.getPrice()));
            cart.getCartItems().add(ci);
            ci.setCart(cart);
            cartItemRepo.save(ci);
        }
        return cartRepo.save(cart);
    }

    public CartItem findAndUpdate(ShoppingCart c, Meal meal, int quanyity){

        CartItem citems = c.getCartItems().stream()
                .filter(ci -> Objects.equals(ci.getMeal().getId(), meal.getId()))
                .findAny()
                .orElse(null);
        if(citems!=null){
            c.getCartItems().remove(citems);
            citems.setQuantity(citems.getQuantity()+quanyity);
            citems.setPrice(BigDecimal.valueOf(citems.getQuantity()).multiply(meal.getPrice()));
        }
        else{
            citems = new CartItem();
            citems.setCart(c);
            citems.setMeal(meal);
            citems.setQuantity(quanyity);
            citems.setPrice(BigDecimal.valueOf(citems.getQuantity()).multiply(meal.getPrice()));

        }
        cartItemRepo.save(citems);
        return citems;
    }
}
