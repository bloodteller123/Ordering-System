package com.zihanc.takeout.controller;


import com.zihanc.takeout.model.Category;
import com.zihanc.takeout.model.Employee;
import com.zihanc.takeout.result.CommonResult;
import com.zihanc.takeout.result.PageResult;
import com.zihanc.takeout.service.CategoryService;
import com.zihanc.takeout.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    private final EmployeeService employeeService;
    @Autowired
    public CategoryController(CategoryService categoryService, EmployeeService employeeService) {
        this.categoryService = categoryService;
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/")
    public CommonResult save(HttpServletRequest request, @RequestBody Category category){
        log.info(category.toString());
        Category c = categoryService.findByName(category.getName());
        if(c!=null)  return CommonResult.error("Category exists");
        Long employee_id = (Long) request.getSession().getAttribute("employee");
        Employee e = employeeService.findById(employee_id).orElse(null);
        if(e==null) return CommonResult.error("Employee error");
//        category.setEmployee(e);
        categoryService.save(category);
        return CommonResult.success("new category added");
    }

    @GetMapping(value = "/page")
    public CommonResult getCategoryPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size){
        PageResult result = null;
        Pageable paging = PageRequest.of(page, size, Sort.by("createTime"));
        Page<Category> pages = categoryService.findAll(paging);
        return CommonResult.success(new PageResult(pages.getTotalPages(), pages.getTotalElements(), pages.getContent()));
    }
    @DeleteMapping(value = "/{id}")
    public CommonResult deleteCategory(@PathVariable(value = "id") Long id){
        log.info(String.valueOf(id));
        if(categoryService.deleteById(id)) return CommonResult.success();
        return CommonResult.error("Category is being used or not exists");
    }
    @PutMapping(value = "/update")
    public CommonResult update(@RequestBody Category category){
        log.info(category.toString());
        Category c = categoryService.findByName(category.getName());
        if(c==null) return CommonResult.error("Something went wrong");
//        c.setUpdateTime(LocalDateTime.now());
//        https://stackoverflow.com/a/53717024/13062745
        categoryService.save(c);
        return CommonResult.success();
    }
}
