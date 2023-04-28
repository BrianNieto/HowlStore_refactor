package com.asj.bootcamp.service;

import com.asj.bootcamp.exception.NotFoundException;
import com.asj.bootcamp.model.entity.Category;
import com.asj.bootcamp.model.request.CategoryRequest;
import com.asj.bootcamp.model.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse createCategory(CategoryRequest categoryRequest) throws RuntimeException;

    CategoryResponse getCategory(Integer id) throws NotFoundException;

    CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest);

    void deleteCategory(Integer id);

    boolean existCategory(String nombreCategory);

    List<CategoryResponse> getAllCategories();
}