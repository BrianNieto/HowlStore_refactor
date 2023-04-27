package com.asj.bootcamp.controller;

import com.asj.bootcamp.dto.CategoryDTO;
import com.asj.bootcamp.dto.ItemCompletoDTO;
import com.asj.bootcamp.dto.ItemDTO;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.mapper.CategoryMapper;
import com.asj.bootcamp.mapper.ItemMapper;
import com.asj.bootcamp.service.CategoryService;
import com.asj.bootcamp.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final CategoryService categoryService;

    public ItemController(ItemService itemService, ItemMapper itemMapper, CategoryService categoryService) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody ItemDTO itemDTO){
        try {
            Item item = itemMapper.itemDTOToItemEntity(itemDTO);
            item.setCategory(categoryService.getCategory(itemDTO.getIdCategory()));

            ItemCompletoDTO tmp = itemMapper.itemEntityToItemDTO(itemService.createItem(item));

            return ResponseEntity.status(HttpStatus.CREATED).body(tmp);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Item ya existente o categoria no encontrada");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Integer id){
        try {
            Item item =  itemService.getItem(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(itemMapper.itemEntityToItemDTO(item));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Integer id, @RequestBody ItemDTO itemDTO){
        try {
            Item tmp = itemMapper.itemDTOToItemEntity(itemDTO);

            tmp.setCategory(categoryService.getCategory(itemDTO.getIdCategory()));
            ItemCompletoDTO updated = itemMapper.itemEntityToItemDTO(itemService.updateItem(id, tmp));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        try {
            itemService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (RuntimeException ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllItems(){
        List<Item> items = itemService.getAllItems();
        List<ItemCompletoDTO> itemCompletoDTOS = new ArrayList<>();
        for (Item item : items){
            itemCompletoDTOS.add(itemMapper.itemEntityToItemDTO(item));
        }

        return ResponseEntity.status(HttpStatus.OK).body(itemCompletoDTOS);
    }

    @GetMapping("/all/{id}")
    public ResponseEntity<?> getAllItemsByCategory(@PathVariable Integer id){
        try {
            List<Item> items = itemService.getAllItemsByCategory(id);
            List<ItemCompletoDTO> itemsDTO = new ArrayList<>();
            for (Item item : items){
                itemsDTO.add(itemMapper.itemEntityToItemDTO(item));
            }
            return ResponseEntity.status(HttpStatus.OK).body(itemsDTO);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Items con esa categoria no encontrados");
        }
    }

}