package com.zihanc.takeout.service.impl;

import com.zihanc.takeout.model.Category;
import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.dto.MealDto;
import com.zihanc.takeout.repository.MealRepo;
import com.zihanc.takeout.service.FlavourService;
import com.zihanc.takeout.service.MealService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MealServiceImpl implements MealService {
    private final MealRepo mealRepo;
    private final FlavourService flavourService;
//    private final CategoryServiceImpl categoryService;
    @Autowired
    public MealServiceImpl(MealRepo mealRepo, FlavourService flavourService) {
        this.mealRepo = mealRepo;
        this.flavourService = flavourService;
//        this.categoryService = categoryService;
    }

    @Override
    public Optional<Meal> findById(Long id){
        return mealRepo.findById(id);
    }

    @Override
    public int countByCategory(Long categoryId) {
//        return mealRepo.countByCategory(categoryId);
        return 0;
    }

    @Override
//    https://www.marcobehler.com/guides/spring-transaction-management-transactional-in-depth
    @Transactional
    public void save(Meal meal) {
//        Category c = categoryService.findByName(meal.getCategoryName());
//        log.info(c.toString());
//        meal.setCategory(c);
        log.info(meal.toString());
        mealRepo.save((Meal) meal);

//        Long mealId = meal.getId();
//        List<Flavour> flavours = meal.getFlavours();
//        flavours = flavours.stream().map(item ->{
//           item.setMealId(mealId);
//           return item;
//        }).collect(Collectors.toList());
//        flavourService.saveAll(flavours);
    }
    @Override
    public Page<Meal> findPageByName(String name, Pageable paging) {
        return mealRepo.findPageByName(name, paging).orElse(null);
    }
    @Override
    public Page<Meal> findAll(Pageable paging){
        return mealRepo.findAll(paging);
    }

    @Override
    public MealDto getMealInfo(Long id) {
        Meal meal = mealRepo.findById(id).orElse(null);
        if(meal==null) return null;

        MealDto dto = new MealDto();
        BeanUtils.copyProperties(meal, dto);
//        dto.setFlavours(flavourService.getFlavoursByMealId(id));
        return dto;
    }

    @Override
    @Transactional
    public void update(Meal meal) {
//        mealRepo.save(mealDto);
        // one meal could have multiple flavours. Maybe it's better to delete them all then add them back
//        flavourService.deleteByMealId(mealDto.getId());
////        mealDto.getFlavours();
//        this.save(mealDto);
    }

    @Override
    public List<Meal> findByCategoryId(Long id) {
        return mealRepo.findByCategoryId(id);
    }

    @Override
    public Meal findByName(String name) {

        return mealRepo.findByName(name).orElse(null);
    }

}
