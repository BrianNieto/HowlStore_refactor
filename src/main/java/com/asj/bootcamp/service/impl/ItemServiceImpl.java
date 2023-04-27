package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.repository.ItemRepository;
import com.asj.bootcamp.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;

    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Item createItem(Item item) {
        Optional<Category> tmp = categoryRepository.findById(item.getCategory().getIdCategoria());

        if (itemExist(item.getNombreItem(), item.getEstadoItem())) {
            throw new RuntimeException(String.format("Item con ese nombre y estado ya registrado"));
        }
        else if (!tmp.isPresent()) {
            throw new RuntimeException(String.format("Categoria con ID: " + item.getCategory().getIdCategoria() + " no registrado"));
        }

        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Integer id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            return optionalItem.get();
        }
        else {
            throw new RuntimeException("Item con id " + id + " no existe");
        }
    }

    @Override
    public Item updateItem(Integer id, Item tmp) {
        Item itempUpdated;
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()){
            itempUpdated = optionalItem.get();
        }
        else {
            throw new RuntimeException("Item con id " + id + " no existe");
        }

        itempUpdated.setNombreItem(tmp.getNombreItem());
        itempUpdated.setPrecioItem(tmp.getPrecioItem());
        itempUpdated.setEstadoItem(tmp.getEstadoItem());
        itempUpdated.setStockItem(tmp.getStockItem());
        itempUpdated.setCategory(tmp.getCategory());

        return itemRepository.save(itempUpdated);
    }

    @Override
    public void deleteItem(Integer id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            itemRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Item con id " + id + " no existe");
        }
    }
    @Override
    public boolean itemExist(String nombreItem, String estadoItem) {
        return itemRepository.findByNombreItemAndEstadoItem(nombreItem, estadoItem).isPresent();
    }

    @Override
    public List<Item> getAllItems(){
        return itemRepository.findAll();
    }


    @Override
    public List<Item> getAllItemsByCategory(Integer idCategory){
        List<Item> items = itemRepository.findByIdCategory(idCategory);
        if (items.isEmpty()){
            throw new RuntimeException("No hay items con la categoria dada");
        }
        return items;
    }

}