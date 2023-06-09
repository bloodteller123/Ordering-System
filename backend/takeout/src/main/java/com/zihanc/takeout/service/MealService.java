package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.dto.MealDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MealService {
    int countByCategory(Long categoryId);
    void save(Meal meal);
    Page<Meal> findPageByName(String name, Pageable paging);

    Page<Meal> findAll(Pageable paging);
    MealDto getMealInfo(Long id);

    void update(Meal meal);

    List<Meal> findByCategoryId(Long id);

    Meal findByName(String name);

    Optional<Meal> findById(Long id);
}
