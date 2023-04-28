package com.asj.bootcamp.model.response;

import com.asj.bootcamp.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    Integer idCategoria;
    String nombreCategoria;

    public static CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .idCategoria(category.getIdCategoria())
                .nombreCategoria(category.getNombreCategoria())
                .build();
    }
}