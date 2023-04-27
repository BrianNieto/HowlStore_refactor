package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Compra;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.repository.CompraRepository;
import com.asj.bootcamp.service.CompraService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CompraServiceImpl.class})
@SpringBootTest
class CompraServiceImplTest {
    @MockBean
    private CompraRepository repository;
    @Autowired
    private CompraService service;

    @Test
    @DisplayName("Compra created")
    void createCompra() {
        Compra compra = DatosDummy.getCompra();

        given(repository.findById(compra.getIdCompra())).willReturn(Optional.of(compra));
        service.createCompra(compra);

        ArgumentCaptor<Compra> compraArgumentCaptor = ArgumentCaptor.forClass(Compra.class);
        verify(repository).save(compraArgumentCaptor.capture());

        Compra compraCaptor = compraArgumentCaptor.getValue();

        assertThat(compraCaptor).isEqualTo(compra);

        verify(repository).save(any());
    }

    @Test
    @DisplayName("Compra found")
    void getCompra() {
        Integer idCompra = 1;

        when(repository.findById(idCompra)).thenReturn(Optional.of(DatosDummy.getCompra()));
        Compra compra = service.getCompra(idCompra);

        assertThat(compra.getIdCompra()).isEqualTo(idCompra);

    }

    @Test
    @DisplayName("Compra not found")
    void getCompraWithException() {
        Integer idCompra = 1;

        when(repository.findById(idCompra)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.getCompra(idCompra)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Compra updated")
    void updateCompra() {
        Integer idCompra = 1;
        Compra compra = new Compra(1,"Pagado", LocalDate.of(2022,03,12), "PAGADO", DatosDummy.getItem(),DatosDummy.getUser());

        given(repository.findById(idCompra)).willReturn(Optional.of(DatosDummy.getCompra()));
        given(service.updateCompra(idCompra,compra)).willReturn(compra);
        Compra compraUpdated = service.updateCompra(idCompra, compra);

        ArgumentCaptor<Compra> compraArgumentCaptor = ArgumentCaptor.forClass(Compra.class);
        verify(repository).save(compraArgumentCaptor.capture());

        assertThat(compraUpdated.getEstadoPedido()).isEqualTo(compra.getEstadoPedido());
        assertThat(compraUpdated.getEstadoPedido()).isEqualTo(compra.getEstadoPedido());

    }

    @Test
    @DisplayName("Compra does not found")
    void updateCompraWithException() {
        Integer idCompra = 1;
        Compra compra = new Compra(1,"Pagado", LocalDate.of(2022,03,12), "PAGADO", DatosDummy.getItem(),DatosDummy.getUser());

        when(repository.findById(idCompra)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateCompra(idCompra,compra)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Compra deleted")
    void deleteCompra() {
        Integer idCompra = 1;

        given(repository.findById(idCompra)).willReturn(Optional.of(DatosDummy.getCompra()));
        willDoNothing().given(repository).deleteById(idCompra);
        service.deleteCompra(idCompra);

        verify(repository,times(1)).deleteById(any());

    }

    @Test
    @DisplayName("Delete compra with exception")
    void deleteCompraWithException() {
        Integer idCompra = 1;

        when(repository.findById(idCompra)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteCompra(idCompra)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Get all compras by idUser")
    void getAllByIdUser(){
        Integer idUser = 1;
        List<Compra> compras = new ArrayList<>();
        compras.add(DatosDummy.getCompra());
        compras.add(DatosDummy.getCompra2());

        given(repository.findByIdUser(idUser)).willReturn(compras);
        List<Compra> compraList = service.findComprasByIdUser(idUser);

        verify(repository,times(1)).findByIdUser(idUser);
        assertThat(compraList.size()).isEqualTo(2);

    }

}