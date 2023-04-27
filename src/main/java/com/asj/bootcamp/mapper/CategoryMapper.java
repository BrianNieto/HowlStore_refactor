package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.CategoryDTO;
import com.asj.bootcamp.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDTO categoryEntityToCategoryDTO(Category category);

    Category categoryDTOToCategoryEntity(CategoryDTO categoryDTO);

}