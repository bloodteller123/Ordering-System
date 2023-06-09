package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {
}
