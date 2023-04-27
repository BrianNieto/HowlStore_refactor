package com.asj.bootcamp.mapper;

import com.asj.bootcamp.dto.RecomendacionDTO;
import com.asj.bootcamp.entity.Recomendacion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecomendacionMapper {

    RecomendacionDTO recomendacionEntityToRecomendacionDTO(Recomendacion recomendacion);

    Recomendacion recomendacionDTOToRecomendacionEntity(RecomendacionDTO recomendacionDTO);

}