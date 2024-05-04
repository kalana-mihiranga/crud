package com.ijse.restapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ijse.restapi.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();
    Category findCategoryById(Long id);
    Category createCategory(Category category);
}
