package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.ShoppingCart;
import com.zihanc.takeout.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepo extends JpaRepository<ShoppingCart, Long> {
//    @Query(value = "SELECT * FROM cart_table WHERE cart_table.userId =: userId", nativeQuery = true)
//    ShoppingCart

    Optional<ShoppingCart> findByUser(User user);
}
