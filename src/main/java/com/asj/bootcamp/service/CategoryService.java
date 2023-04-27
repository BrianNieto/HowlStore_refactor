package com.asj.bootcamp.service;

import com.asj.bootcamp.entity.Category;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);

    Category getCategory(Integer id);

    Category updateCategory(Integer id, Category tmp);

    void deleteCategory(Integer id);

    boolean existCategory(String nombreCategory);

    List<Category> getAllCategories();
}