package com.asj.bootcamp.service;

import com.asj.bootcamp.entity.Compra;

import java.util.List;

public interface CompraService {

    Compra createCompra(Compra compra);

    Compra getCompra(Integer id);

    Compra updateCompra(Integer id, Compra tmp);

    void deleteCompra(Integer id);

    List<Compra> findComprasByIdUser(Integer idUser);
}