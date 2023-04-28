package com.asj.bootcamp.model.request;

import com.asj.bootcamp.model.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequest {
    @NotBlank(message = "nombreCategoria no debe estar vacío")
    String nombreCategoria;

    public static Category toEntity(CategoryRequest request){
        return Category.builder()
                .nombreCategoria(request.nombreCategoria)
                .build();
    }
}