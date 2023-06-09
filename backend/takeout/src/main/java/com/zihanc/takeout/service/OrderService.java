package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Order;

public interface OrderService {
    void submit(Long userId,Order order);
}
