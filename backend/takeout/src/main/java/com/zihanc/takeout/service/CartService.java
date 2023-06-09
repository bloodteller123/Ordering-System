package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.ShoppingCart;
import com.zihanc.takeout.model.User;

public interface CartService {
    ShoppingCart findByUser(User user);

    ShoppingCart save(ShoppingCart cart);

    ShoppingCart updateCart(User user, Meal meal, int quanyity);
}
