package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.exception.NotFoundException;
import com.asj.bootcamp.model.entity.Category;
import com.asj.bootcamp.model.request.CategoryRequest;
import com.asj.bootcamp.model.response.CategoryResponse;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) throws RuntimeException{
        //modificar excepcion
        if (repository.existsByNombreCategoria(categoryRequest.getNombreCategoria()))
            throw new RuntimeException(String.format("Categoria %s nombre ya registrado", categoryRequest.getNombreCategoria()));

        return CategoryResponse.toResponse(repository.save(CategoryRequest.toEntity(categoryRequest)));
    }

    @Override
    public CategoryResponse getCategory(Integer id) throws NotFoundException{
        return CategoryResponse.toResponse(repository.findById(id).orElseThrow(()
                                                                -> new NotFoundException("Categoria no encontrada")));
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest tmp) {

        Category categoryToUpdate = repository.findById(id).orElseThrow(()
                                                                -> new NotFoundException("Categoria no encontrada"));
        //falta excepcion si ya existe la categoria con ese nombre

        categoryToUpdate.setNombreCategoria(tmp.getNombreCategoria());

        return CategoryResponse.toResponse(repository.save(categoryToUpdate));
    }

    @Override
    public void deleteCategory(Integer id) {
        repository.findById(id).orElseThrow(() -> new NotFoundException("Categoria no encontrada"));
        repository.deleteById(id);
    }

    @Override
    public boolean existCategory(String nombreCategory) {
        return repository.findByNombreCategoria(nombreCategory).isPresent();
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> categoriesEntities = repository.findAll();
        List<CategoryResponse> categoryResponses = new ArrayList<>();
        categoriesEntities.forEach((category) -> {
            categoryResponses.add(CategoryResponse.toResponse(category));
        });
        return categoryResponses;
    }

}