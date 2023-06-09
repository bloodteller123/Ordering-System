package com.zihanc.takeout.repository;

import com.zihanc.takeout.model.Employee;
import com.zihanc.takeout.model.Meal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface MealRepo extends JpaRepository<Meal,Long> {
//    @Query(value = "SELECT COUNT(*) FROM meal_table m " +
//            "WHERE m.category_id =:categoryId", nativeQuery = true)
//    int countByCategory(Long categoryId);
    Optional<Page<Meal>> findPageByName(String name, Pageable page);

//    https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Meal> findByCategoryId(Long categoryId);

    Optional<Meal> findByName(String name);
}
