package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.CompraCompletaDTO;
import com.asj.bootcamp.entity.Compra;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CompraMapper {

    CompraCompletaDTO compraEntityToCompraCompletaDTO(Compra compra);
    Compra compraDTOToEntity(CompraCompletaDTO compraDTO);

}