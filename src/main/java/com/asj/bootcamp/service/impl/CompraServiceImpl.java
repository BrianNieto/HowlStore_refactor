package com.asj.bootcamp.service.impl;


import com.asj.bootcamp.entity.Compra;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.exception.NotFoundException;
import com.asj.bootcamp.repository.CompraRepository;
import com.asj.bootcamp.service.CompraService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraServiceImpl implements CompraService {

    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    @Override
    public Compra createCompra(Compra compra) {
        return compraRepository.save(compra);
    }

    @Override
    public Compra getCompra(Integer id){
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            return optionalCompra.get();
        }
        else {
            throw new RuntimeException("Compra con id " + id + " no existe");
        }

    }

    @Override
    public Compra updateCompra(Integer id, Compra tmp) {
        Compra compraUpdated;
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()){
            compraUpdated = optionalCompra.get();
        }
        else {
            throw new RuntimeException("Compra con id " + id + " no existe");
        }
        compraUpdated.setComentario(tmp.getComentario());
        compraUpdated.setEstadoPedido(tmp.getEstadoPedido());

        return compraRepository.save(compraUpdated);
    }

    @Override
    public void deleteCompra(Integer id) {
        Optional<Compra> optionalCompra = compraRepository.findById(id);
        if (optionalCompra.isPresent()) {
            compraRepository.deleteById(id);
        }
        else {
            throw new RuntimeException("Compra con id " + id + " no existe");
        }
    }

    @Override
    public List<Compra> findComprasByIdUser(Integer idUser){
        return compraRepository.findByIdUser(idUser);
    }

}