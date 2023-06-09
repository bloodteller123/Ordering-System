package com.zihanc.takeout.controller;

import com.zihanc.takeout.model.Order;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
@Slf4j
public class OrderController {
    private final OrderService orderService;
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/submit")
    public CommonResult submit(HttpServletRequest request, @RequestBody Order order){
        log.info(order.toString());
        Long userId = (Long)request.getSession().getAttribute("userId");
        orderService.submit(userId, order);
        return CommonResult.success();
    }
}
