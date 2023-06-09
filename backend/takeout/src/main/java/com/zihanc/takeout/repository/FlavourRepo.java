package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.Flavour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlavourRepo extends JpaRepository<Flavour,Long> {
    @Query(value = "SELECT * FROM flav_table WHERE flav_table.meal_id =: mealId", nativeQuery = true)
    Optional<List<Flavour>> getFlavoursByMealId(Long mealId);
    @Query(value = "DELETE FROM flav_table WHERE flav_table.meal_id =: mealId", nativeQuery = true)
    void deleteByMealId(Long mealId);
}
