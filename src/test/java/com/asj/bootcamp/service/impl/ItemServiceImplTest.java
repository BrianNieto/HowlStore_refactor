package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.repository.ItemRepository;
import com.asj.bootcamp.service.ItemService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {ItemServiceImpl.class})
@SpringBootTest
class ItemServiceImplTest {

    @MockBean
    private ItemRepository itemRepository;
    @MockBean
    private CategoryRepository categoryRepository;
    @Autowired
    private ItemService service;

    @Test
    @DisplayName("Item created")
    void createItem() {
        Item item = DatosDummy.getItem();

        given(categoryRepository.findById(item.getCategory().getIdCategoria())).willReturn(Optional.of(item.getCategory()));
        service.createItem(item);

        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(itemArgumentCaptor.capture());

        Item itemCaptor = itemArgumentCaptor.getValue();

        assertThat(itemCaptor).isEqualTo(item);

        verify(itemRepository).save(any());
    }

    @Test
    @DisplayName("Item with nombre and estado already exist")
    void createItemAlreadyExist(){
        Item item = DatosDummy.getItem();

        given(categoryRepository.findById(item.getCategory().getIdCategoria())).willReturn(Optional.of(item.getCategory()));
        given(itemRepository.findByNombreItemAndEstadoItem(item.getNombreItem(),item.getEstadoItem())).willReturn(Optional.of(item));
        assertThatThrownBy(() -> service.createItem(item)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item with given category not exist")
    void createItemWithoutCategory(){
        Item item = DatosDummy.getItem();

        given(categoryRepository.findById(item.getCategory().getIdCategoria())).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.createItem(item)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item found")
    void getItem() {
        Integer idItem = 1;

        when(itemRepository.findById(idItem)).thenReturn(Optional.of(DatosDummy.getItem()));
        Item item = service.getItem(idItem);

        assertThat(item.getIdItem()).isEqualTo(idItem);

    }

    @Test
    @DisplayName("Item not found")
    void getItemWithException() {
        Integer idItem = 1;

        when(itemRepository.findById(idItem)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getItem(idItem)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item updated")
    void updateItem() {
        Integer idItem = 1;
        Integer idCategory = 1;
        Item itemToUpdate = new Item(1,"M9 Bayonet | Tiger Tooth", 45000, 3, "Factory New", "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", DatosDummy.getItem().getCategory());

        given(itemRepository.findById(idItem)).willReturn(Optional.of(DatosDummy.getItem()));
        given(categoryRepository.findById(idCategory)).willReturn(Optional.of(DatosDummy.getItem().getCategory()));
        given(service.updateItem(idItem,itemToUpdate)).willReturn(itemToUpdate);
        Item itemUpdated = service.updateItem(idItem, itemToUpdate);

        ArgumentCaptor<Item> itemArgumentCaptor = ArgumentCaptor.forClass(Item.class);
        verify(itemRepository).save(itemArgumentCaptor.capture());

        assertThat(itemUpdated.getPrecioItem()).isEqualTo(itemToUpdate.getPrecioItem());
        assertThat(itemUpdated.getStockItem()).isEqualTo(itemToUpdate.getStockItem());
    }

    @Test
    @DisplayName("Item with category not existing")
    void updateItemWithoutExistingCategory() {
        Integer idItem = 1;
        Integer idCategory = 1;
        Category tmp = new Category(2,"Cuchillo");
        Item itemToUpdate = new Item(1,"M9 Bayonet | Tiger Tooth", 45000, 3, "Factory New", "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", tmp);

        given(categoryRepository.findById(idCategory)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateItem(idItem,itemToUpdate)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item without exist")
    void updateItemWithoutExisting() {
        Integer idItem = 1;
        Integer idCategory = 1;
        Category tmp = new Category(2,"Cuchillo");
        Item itemToUpdate = new Item(1,"M9 Bayonet | Tiger Tooth", 45000, 3, "Factory New", "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", tmp);

        given(categoryRepository.findById(idCategory)).willReturn(Optional.of(DatosDummy.getItem().getCategory()));
        given(itemRepository.findById(idItem)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateItem(idItem,itemToUpdate)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item deleted")
    void deleteItem() {
        Integer idItem = 1;

        given(itemRepository.findById(idItem)).willReturn(Optional.of(DatosDummy.getItem()));
        willDoNothing().given(itemRepository).deleteById(idItem);
        service.deleteItem(idItem);

        verify(itemRepository,times(1)).deleteById(any());

    }

    @Test
    @DisplayName("Delete item with exception")
    void deleteItemWithException() {
        Integer idItem = 1;

        given(itemRepository.findById(idItem)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteItem(idItem)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Item exist with nombreItem and estadoItem")
    void itemExist() {
        String nombreItem = "M9 Bayonet | Tiger Tooth";
        String estadoItem = "Factory New";

        given(itemRepository.findByNombreItemAndEstadoItem(nombreItem,estadoItem)).willReturn(Optional.of(DatosDummy.getItem()));
        Boolean itemExist = service.itemExist(nombreItem,estadoItem);

        assertThat(itemExist).isTrue();

    }

    @Test
    @DisplayName("Get all items")
    void getAllItems() {
        List<Item> items = new ArrayList<>();
        items.add(DatosDummy.getItem());
        items.add(DatosDummy.getItem2());

        given(itemRepository.findAll()).willReturn(items);
        List<Item> itemList = service.getAllItems();

        verify(itemRepository,times(1)).findAll();
        assertThat(itemList.size()).isEqualTo(2);

    }

    @Test
    @DisplayName("Get all items by category")
    void getAllItemsByCategory(){
        List<Item> items = new ArrayList<>();
        items.add(DatosDummy.getItem2());
        items.add(DatosDummy.getItem3());

        given(itemRepository.findByIdCategory(DatosDummy.getItem3().getCategory().getIdCategoria())).willReturn(items);
        List<Item> itemList = service.getAllItemsByCategory(DatosDummy.getItem3().getCategory().getIdCategoria());

        verify(itemRepository,times(1)).findByIdCategory(DatosDummy.getItem3().getCategory().getIdCategoria());
        assertThat(itemList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Get all items by category but is empty")
    void getAllItemsByCategoryEmpty(){
        Integer idCategory = 1;
        List<Item> items = new ArrayList<>();

        given(itemRepository.findByIdCategory(idCategory)).willReturn(items);

        assertThatThrownBy(() -> service.getAllItemsByCategory(idCategory)).isInstanceOf(RuntimeException.class);
    }


}