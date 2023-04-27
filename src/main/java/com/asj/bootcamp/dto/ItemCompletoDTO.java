package com.asj.bootcamp.dto;

import com.asj.bootcamp.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ItemCompletoDTO {

    Integer idItem;
    String nombreItem;
    Integer precioItem;
    Integer stockItem;
    String estadoItem;
    String img1;
    String img2;
    String img3;
    Category category;

}