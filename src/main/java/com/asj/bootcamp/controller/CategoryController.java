package com.asj.bootcamp.controller;

import com.asj.bootcamp.dto.CategoryDTO;
import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.mapper.CategoryMapper;
import com.asj.bootcamp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService service;

    private final CategoryMapper mapper;

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO){
        try{
            Category category = mapper.categoryDTOToCategoryEntity(categoryDTO);

            CategoryDTO tmp = mapper.categoryEntityToCategoryDTO(service.createCategory(category));
            return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una categoria con ese nombre");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id){
        try {
            Category category =  service.getCategory(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.categoryEntityToCategoryDTO(category));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){
        try {
            Category tmp = mapper.categoryDTOToCategoryEntity(categoryDTO);
            CategoryDTO updated = mapper.categoryEntityToCategoryDTO(service.updateCategory(id, tmp));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        }
        catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        try {
            service.deleteCategory(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria no encontrada");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        List<Category> categories = service.getAllCategories();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category : categories){
            categoryDTOS.add(mapper.categoryEntityToCategoryDTO(category));
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoryDTOS);
    }

}