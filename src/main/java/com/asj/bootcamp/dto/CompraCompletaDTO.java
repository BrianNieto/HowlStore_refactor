package com.asj.bootcamp.dto;

import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CompraCompletaDTO {
    Integer idCompra;
    String comentario;
    LocalDate fecha;
    String estadoPedido;
    Item item;
    User user;

}