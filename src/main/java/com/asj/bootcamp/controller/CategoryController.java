package com.asj.bootcamp.controller;

import com.asj.bootcamp.model.request.CategoryRequest;
import com.asj.bootcamp.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/categorias")
public class CategoryController {

    private final CategoryService service;

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCategory(categoryRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok(service.getCategory(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Integer id, @RequestBody CategoryRequest categoryRequest){
        return ResponseEntity.ok(service.updateCategory(id,categoryRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping
    public ResponseEntity<?> getAllCategory(){
        return ResponseEntity.ok(service.getAllCategories());
    }

}