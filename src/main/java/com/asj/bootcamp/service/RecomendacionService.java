package com.asj.bootcamp.service;

import com.asj.bootcamp.entity.Recomendacion;

import java.util.List;

public interface RecomendacionService {

    Recomendacion createRecomendacion(Recomendacion recomendacion);

    Recomendacion getRecomendacion(Integer id);

    Recomendacion updateRecomendacion(Integer id, Recomendacion tmp);

    void deleteRecomendacion(Integer id);

    List<Recomendacion> getAllRecomendaciones();

}