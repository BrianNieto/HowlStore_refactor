package com.asj.bootcamp.repository;

import com.asj.bootcamp.entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Integer> {

    @Query("SELECT compra FROM Compra compra WHERE compra.user.idUser = ?1")
    List<Compra> findByIdUser(Integer id);

}