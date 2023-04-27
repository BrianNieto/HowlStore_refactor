package com.asj.bootcamp.controller;

import com.asj.bootcamp.mapper.ItemMapper;
import com.asj.bootcamp.service.CategoryService;
import com.asj.bootcamp.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ItemService itemService;
    @MockBean
    private CategoryService categoryService;
    @MockBean
    private ItemMapper itemMapper;

    @Test
    void createItem() {

    }

    @Test
    void getItem() {
    }

    @Test
    void updateItem() {
    }

    @Test
    void deleteItem() {
    }

    @Test
    void getAllItems() {
    }

    @Test
    void getAllItemsByCategory() {
    }

}