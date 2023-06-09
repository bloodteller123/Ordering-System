package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.Order;
import com.zihanc.takeout.service.CartService;
import com.zihanc.takeout.service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final CartService cartService;
    @Autowired
    public OrderServiceImpl(CartService cartService) {
        this.cartService = cartService;
    }

    @Override
    @Transactional
    public void submit(Long userId, Order order) {

    }
}
