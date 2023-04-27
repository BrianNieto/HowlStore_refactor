package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.entity.Recomendacion;
import com.asj.bootcamp.repository.RecomendacionRepository;
import com.asj.bootcamp.service.RecomendacionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecomendacionServiceImpl implements RecomendacionService {

    private final RecomendacionRepository repository;

    public RecomendacionServiceImpl(RecomendacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Recomendacion createRecomendacion(Recomendacion recomendacion) {
        return repository.save(recomendacion);
    }

    @Override
    public Recomendacion getRecomendacion(Integer id) {
        Optional<Recomendacion> optionalRecomendacion = repository.findById(id);
        if (optionalRecomendacion.isPresent()) {
            return optionalRecomendacion.get();
        }
        else {
            throw new RuntimeException("Recomendacion con id " + id + " no existe");
        }
    }

    @Override
    public Recomendacion updateRecomendacion(Integer id, Recomendacion tmp) {
        Recomendacion recomendacionUpdated;
        Optional<Recomendacion> optionalRecomendacion = repository.findById(id);
        if (optionalRecomendacion.isPresent()){
            recomendacionUpdated = optionalRecomendacion.get();
        }
        else {
            throw new RuntimeException("Recomendacion con id " + id + " no existe");
        }
        recomendacionUpdated.setComentarioRecomendacion(tmp.getComentarioRecomendacion());
        recomendacionUpdated.setImgRecomendacion(tmp.getImgRecomendacion());

        return repository.save(recomendacionUpdated);
    }

    @Override
    public void deleteRecomendacion(Integer id) {
        Optional<Recomendacion> optionalRecomendacion = repository.findById(id);
        if (optionalRecomendacion.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new RuntimeException("Recomendacion con id " + id + " no existe");
        }
    }

    @Override
    public List<Recomendacion> getAllRecomendaciones() {
        return repository.findAll();
    }

}