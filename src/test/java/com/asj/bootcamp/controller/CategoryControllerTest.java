package com.asj.bootcamp.controller;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.dto.CategoryDTO;
import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.mapper.CategoryMapper;
import com.asj.bootcamp.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CategoryService service;
    @MockBean
    private CategoryMapper categoryMapper;

    @Test
    @DisplayName("Category created with status CREATED")
    void createCategory() throws Exception {
        CategoryDTO categoryDTO = DatosDummy.getCategorySMGDTO();

        when(service.createCategory(any())).thenReturn(DatosDummy.getCategorySMG());

        mockMvc.perform(
                        post("/categorias")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isCreated());

        verify(service,times(1)).createCategory(any());
    }

    @Test
    @DisplayName("Category created with status CONFLICT")
    void createCategoryWithException() throws Exception {
        CategoryDTO categoryDTO = DatosDummy.getCategorySMGDTO();

        when(service.createCategory(any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        post("/categorias")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(categoryDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Get category by id with status ACCEPTED")
    void getCategory() throws Exception {
        Integer id = 1;

        when(service.getCategory(any())).thenReturn(DatosDummy.getCategorySMG());
        mockMvc.perform(
                        get("/categorias" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).getCategory(any());

    }

    @Test
    @DisplayName("Get category by id with status NOT FOUND")
    void getCategoryWithException() throws Exception {
        Integer id = 1;

        when(service.getCategory(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(
                        get("/categorias" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).getCategory(any());

    }
    @Test
    @DisplayName("Update category with status ACCEPTED")
    void updateCategory() throws Exception {
        Integer id = 1;
        CategoryDTO categoryDTO = DatosDummy.getCategorySMGDTO();

        when(service.updateCategory(any(),any())).thenReturn(DatosDummy.getCategorySMG());

        mockMvc.perform(
                        put("/categorias" + "/{id}", id)
                                .content(mapper.writeValueAsString(categoryDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(service,times(1)).updateCategory(any(),any());
    }

    @Test
    @DisplayName("Update category with status NOT FOUND")
    void updateCategoryWithException() throws Exception {
        Integer id = 1;
        CategoryDTO categoryDTO = DatosDummy.getCategorySMGDTO();

        when(service.updateCategory(any(),any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        put("/categorias" + "/{id}", id)
                                .content(mapper.writeValueAsString(categoryDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service,times(1)).updateCategory(any(),any());
    }

    @Test
    @DisplayName("Delete category with status ACCEPTED")
    void deleteCategory() throws Exception {
        Integer id = 1;

        doNothing().when(service).deleteCategory(id);

        mockMvc.perform(
                        delete("/categorias" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).deleteCategory(any());
    }

    @Test
    @DisplayName("Delete category with status NOT FOUND")
    void deleteCategoryWithException() throws Exception {
        Integer id = 1;

        doThrow(IllegalArgumentException.class).when(service).deleteCategory(id);

        mockMvc.perform(
                        delete("/categorias" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).deleteCategory(any());
    }

    @Test
    void getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(DatosDummy.getCategorySMG());
        categories.add(DatosDummy.getCategoryRifles());

        when(service.getAllCategories()).thenReturn(categories);

        mockMvc.perform(
                        get("/categorias"))
                .andExpect(status().isOk());
        verify(service,times(1)).getAllCategories();

    }

}