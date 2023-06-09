package com.zihanc.takeout.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zihanc.takeout.model.Category;
import com.zihanc.takeout.model.Meal;
import com.zihanc.takeout.model.dto.MealDto;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.service.CategoryService;
import com.zihanc.takeout.service.FlavourService;
import com.zihanc.takeout.service.MealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/meal")
@Slf4j
public class MealController {
    private final MealService mealService;
    private final FlavourService flavourService;
    private final CategoryService categoryService;
    private final RedisTemplate redisTemplate;
    @Autowired
    public MealController(MealService mealService, FlavourService flavourService, CategoryService categoryService, RedisTemplate redisTemplate) {
        this.mealService = mealService;
        this.flavourService = flavourService;
        this.categoryService = categoryService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/")
    public CommonResult saveMeal(@RequestBody MealDto mealDto){
        log.info(mealDto.toString());
        // by design, categryname is whatever name already in the table.
        Category c = categoryService.findByName(mealDto.getCategoryName());
        log.info(c.toString());
        String key = "meal_"+c.getId();
        //reset redistemplate
        redisTemplate.delete(key);

        Meal meal = new Meal();
        BeanUtils.copyProperties(mealDto, meal);
        log.info(meal.toString());
        c.addMeal(meal);
        //https://stackoverflow.com/a/44263632/13062745
        meal.setCategory(c);
        mealService.save(meal);

//        categoryService.save(c);
        return CommonResult.success();
    }

    @PutMapping("/")
    public CommonResult updateMeal(@RequestBody MealDto mealDto){
        log.info(mealDto.toString());
        Category c = categoryService.findByName(mealDto.getCategoryName());
        String key = "meal_"+c.getId();
        redisTemplate.delete(key);

        mealService.update(mealDto);
        return CommonResult.success();
    }

//    @GetMapping(value = "/page")
    // page index starts at 0;
//    public CommonResult getMealsPageByName(
//            String name,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size){
//        PageResult response = null;
//        // filed name or column name? for now lets use field name
//        Pageable paging = PageRequest.of(page, size, Sort.by("createTime"));
//        Page<Meal> results;
//        if(name!=null) {
//            results = mealService.findPageByName(name, paging);
//            if(results==null)  return CommonResult.error(null);
//            log.info(results.getContent().toString());
//        }else{
//            results = mealService.findAll(paging);
//        }
//        response = new PageResult(results.getTotalPages(),
//                results.getTotalElements(),
//                results.getContent().stream().map(item ->{
//                    MealDto md = new MealDto();
////                    https://stackoverflow.com/questions/12217952/copy-fields-from-its-parent-class-in-java
//                    BeanUtils.copyProperties(item, md);
//                    Long categoryId = item.getCategoryId();
//                    Category c = categoryService.findById(categoryId);
//                    if(c==null) return item;
//                    md.setCategoryName(c.getName());
//                    return md;
//                }).collect(Collectors.toList()));
//        //TODO: maybe use dto?
//        return CommonResult.success(response);
//    }

    @GetMapping("/{id}")
    public CommonResult getMealInfo(@PathVariable Long id){
        log.info(String.valueOf(id));
        if(id==null) return CommonResult.error("Meal not exists");
        return CommonResult.success(mealService.getMealInfo(id));
    }

    @GetMapping("/list")
    public CommonResult list(@RequestParam Long categoryId){
        log.info(categoryId.toString());
        String key = "meal_"+String.valueOf(categoryId);
        // cache meal by category id
        List<Meal> meals = (List<Meal>) redisTemplate.opsForValue().get(key);
//        String val = (String)redisTemplate.opsForValue().get(key);
//        List<MealDto> mealDtoList = null;
//        if(val!=null) return CommonResult.success(
//                new ArrayList<>(Arrays.asList(new Gson().fromJson(val, MealDto[].class))));
        if(meals!=null) return CommonResult.success(meals);
        meals= mealService.findByCategoryId(categoryId);
//        mealDtoList = meals.stream().map(item ->{
//            MealDto dto = new MealDto();
//            BeanUtils.copyProperties(item, dto);
//            dto.setCategoryName(item.getCategory().getName());
//            //some flavrours?
//            return dto;
//        }).collect(Collectors.toList());
        log.info(meals.toString());
        redisTemplate.opsForValue().set(key,  (meals), 60, TimeUnit.MINUTES);


        return CommonResult.success(meals);
    }
}
