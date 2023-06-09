package com.zihanc.takeout.service;

import com.zihanc.takeout.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    void save(Category c);
    Category findByName(String name);
    Page<Category> findAll(Pageable paging);
    boolean deleteById(Long id);
    Category findById(Long id);
}
