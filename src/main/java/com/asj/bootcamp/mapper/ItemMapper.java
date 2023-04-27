package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.ItemCompletoDTO;
import com.asj.bootcamp.dto.ItemDTO;
import com.asj.bootcamp.entity.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemCompletoDTO itemEntityToItemDTO(Item item);

    Item itemDTOToItemEntity(ItemDTO itemDTO);

    Item itemCompletoDTOToItemEntity(ItemCompletoDTO itemCompletoDTO);

}