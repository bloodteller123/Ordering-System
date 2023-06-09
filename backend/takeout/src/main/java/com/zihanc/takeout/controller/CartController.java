package com.zihanc.takeout.controller;

import com.zihanc.takeout.model.CartItem;
import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.ShoppingCart;
import com.zihanc.takeout.model.User;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.service.CartService;
import com.zihanc.takeout.service.MealService;
import com.zihanc.takeout.service.UserService;
import com.zihanc.takeout.utils.Storage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/cart")
@Slf4j
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final MealService mealService;
    @Autowired
    public CartController(CartService cartService, UserService userService, MealService mealService) {
        this.cartService = cartService;
        this.userService = userService;
        this.mealService = mealService;
    }

    @PostMapping("/add")
    public CommonResult addToCart(@RequestBody CartItem cartItem){
        log.info(cartItem.toString());
        Long userId = Storage.getId();
        User user = userService.findById(userId);
        if(user==null) return CommonResult.error("user id error");
//        cart.setUser(user);
        Meal meal = mealService.findById(cartItem.getMeal().getId()).orElse(null);
//        if(meal==null) return CommonResult.error("user id error");
        // assuming
        ShoppingCart cart = cartService.updateCart(user, cartItem.getMeal(), cartItem.getQuantity());

        return CommonResult.success(cart);
    }
}
