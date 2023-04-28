package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.model.entity.Category;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.service.CategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {CategoryServiceImpl.class})
@SpringBootTest
class CategoryServiceImplTest {

    @MockBean
    private CategoryRepository repository;
    @Autowired
    private CategoryService service;

    @Test
    @DisplayName("Category created")
    void createCategory() {
        Category categorySMG = DatosDummy.getCategorySMG();
        service.createCategory(categorySMG);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(repository).save(categoryArgumentCaptor.capture());

        Category categoryCaptor = categoryArgumentCaptor.getValue();

        assertThat(categoryCaptor).isEqualTo(categorySMG);

        verify(repository).save(categorySMG);
    }

    @Test
    @DisplayName("Category with exception")
    void createCategoryWithException(){
        Category categoryRifles = DatosDummy.getCategoryRifles();

        given(repository.findByNombreCategoria(categoryRifles.getNombreCategoria())).willReturn(Optional.of(categoryRifles));

        assertThatThrownBy(() -> service.createCategory(categoryRifles)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Category found")
    void getCategory() {
        Integer idCategory = 2;

        when(this.repository.findById(idCategory)).thenReturn(Optional.of(DatosDummy.getCategoryRifles()));
        Category category = service.getCategory(idCategory);

        assertThat(category.getIdCategoria()).isEqualTo(2);
        assertThat(category.getNombreCategoria()).isEqualTo("Rifles");
    }

    @Test
    @DisplayName("Category not found")
    void getCategoryWithException(){
        Integer idCategory = 2;

        given(repository.findById(idCategory)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCategory(idCategory)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Category updated")
    void updateCategory() {
        Integer idCategory = 1;
        Category categoryToUpdate = new Category(1,"Pistolas");

        given(repository.findById(idCategory)).willReturn(Optional.of(DatosDummy.getCategorySMG()));
        given(service.updateCategory(idCategory,categoryToUpdate)).willReturn(categoryToUpdate);
        Category updated = service.updateCategory(idCategory,categoryToUpdate);

        ArgumentCaptor<Category> categoryArgumentCaptor = ArgumentCaptor.forClass(Category.class);
        verify(repository).save(categoryArgumentCaptor.capture());

        assertThat(updated.getNombreCategoria()).isEqualTo("Pistolas");
    }

    @Test
    @DisplayName("Category not found to update")
    void updateCategoryWithException(){
        Integer idCategory = 1;
        Category categoryToUpdate = new Category(1,"Pistolas");

        given(repository.findById(idCategory)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateCategory(idCategory,categoryToUpdate)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Category deleted successfully")
    void deleteCategory() {
        Integer idCategory = 1;

        given(repository.findById(idCategory)).willReturn(Optional.of(DatosDummy.getCategorySMG()));
        willDoNothing().given(repository).deleteById(idCategory);
        service.deleteCategory(idCategory);

        verify(repository,times(1)).deleteById(any());

    }

    @Test
    @DisplayName("Category to delete not found")
    void deleteCategoryWithException(){
        Integer idCategory = 1;

        given(repository.findById(idCategory)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteCategory(idCategory)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Get all categories")
    void getAllCAtegories(){
        List<Category> categories = new ArrayList<>();
        categories.add(DatosDummy.getCategorySMG());
        categories.add(DatosDummy.getCategoryRifles());

        given(repository.findAll()).willReturn(categories);
        List<Category> categoryList = service.getAllCategories();

        verify(repository,times(1)).findAll();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Category exist")
    void existCategory(){
        String category = "test@test.com";

        given(repository.findByNombreCategoria(category)).willReturn(Optional.of(DatosDummy.getCategorySMG()));

        Boolean existUserByEmail = service.existCategory(category);

        assertThat(existUserByEmail).isTrue();
    }

}