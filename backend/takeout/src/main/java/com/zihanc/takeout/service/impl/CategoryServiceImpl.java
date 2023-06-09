package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.Category;
import com.zihanc.takeout.repository.CategoryRepo;
import com.zihanc.takeout.service.CategoryService;
import com.zihanc.takeout.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepo categoryRepo;
//    private final MealService mealService;
    @Autowired
    public CategoryServiceImpl(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
//        this.mealService = mealService;
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);
    }

    @Override
    public Category findByName(String name) {
        Category c = categoryRepo.findByName(name).orElse(null);
        return c;
    }
    @Override
    public Page<Category> findAll(Pageable paging){
        return categoryRepo.findAll(paging);
    }

    @Override
    // delete by item--> check if current category is used
    public boolean deleteById(Long id) {
        if(categoryRepo.findById(id).isPresent()){
            if(categoryRepo.countMeals(id)>0) return false;
            categoryRepo.deleteById(id);
            return true;
        }
        return false;

    }

    @Override
    public Category findById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }
}
