package com.asj.bootcamp.repository;

import com.asj.bootcamp.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Integer> {

    Optional<Item> findByNombreItemAndEstadoItem(String nombreItem, String estadoItem);

    @Query("SELECT item FROM Item item WHERE item.category.idCategoria = ?1")
    List<Item> findByIdCategory(Integer id);

}