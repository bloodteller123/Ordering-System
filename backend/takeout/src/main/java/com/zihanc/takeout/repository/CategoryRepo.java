package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM category_table WHERE category_table.name = :name", nativeQuery = true)
    Optional<Category> findByName(String name);
    @Query(value = "select count(m) from category_table c join c.meals m where c.id =:id", nativeQuery = true)
    long countMeals(Long id);
}
