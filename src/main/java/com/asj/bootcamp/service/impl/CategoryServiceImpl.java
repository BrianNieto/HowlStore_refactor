package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.exception.NotFoundException;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Category createCategory(Category category) {
        if (existCategory(category.getNombreCategoria())) {
            throw new RuntimeException(String.format("Categoria %s nombre ya registrado", category.getNombreCategoria()));
        }
        return repository.save(category);
    }

    @Override
    public Category getCategory(Integer id){
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        else {
            throw new RuntimeException("Categoria con id " + id + " no existe");
        }
    }

    @Override
    public Category updateCategory(Integer id, Category tmp) {
        Category categoryUpdated;
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()){
            categoryUpdated = optionalCategory.get();
        }
        else {
            throw new RuntimeException("Categoria con id " + id + " no existe");
        }
        categoryUpdated.setNombreCategoria(tmp.getNombreCategoria());

        return repository.save(categoryUpdated);
    }

    @Override
    public void deleteCategory(Integer id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new RuntimeException("Categoria con id " + id + " no existe");
        }
    }

    @Override
    public boolean existCategory(String nombreCategory) {
        return repository.findByNombreCategoria(nombreCategory).isPresent();
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

}