package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Recomendacion;
import com.asj.bootcamp.repository.RecomendacionRepository;
import com.asj.bootcamp.service.RecomendacionService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {RecomendacionServiceImpl.class})
@SpringBootTest
class RecomendacionServiceImplTest {

    @MockBean
    private RecomendacionRepository repository;
    @Autowired
    private RecomendacionService service;

    @Test
    @DisplayName("Recomendacion created")
    void createRecomendacion() {
        Recomendacion recomendacion = DatosDummy.getRecomendacion();
        service.createRecomendacion(recomendacion);

        ArgumentCaptor<Recomendacion> recomendacionArgumentCaptor = ArgumentCaptor.forClass(Recomendacion.class);
        verify(repository).save(recomendacionArgumentCaptor.capture());

        Recomendacion recomendacionCaptor = recomendacionArgumentCaptor.getValue();

        assertThat(recomendacionCaptor).isEqualTo(recomendacion);

        verify(repository).save(any());
    }

    @Test
    @DisplayName("Recomendacion found")
    void getRecomedacion() {
        Integer idRecomendacion = 1;

        when(this.repository.findById(idRecomendacion)).thenReturn(Optional.of(DatosDummy.getRecomendacion()));
        Recomendacion contact = service.getRecomendacion(idRecomendacion);

        assertThat(contact.getIdRecomendacion()).isEqualTo(1);
    }

    @Test
    @DisplayName("Recomendacion not found")
    void getRecomedacionWithException(){
        Integer idRecomendacion = 1;

        given(repository.findById(idRecomendacion)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.getRecomendacion(idRecomendacion)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Recomendacion updated")
    void updateRecomedacion() {
        Integer idRecomendacion = 1;
        Recomendacion recomedacionToUpdate = new Recomendacion(1,"Naim Cambe", "comentario actualizado",  LocalDate.of(2021,04,23), "img/imgRecomendado.jpg");

        given(repository.findById(idRecomendacion)).willReturn(Optional.of(DatosDummy.getRecomendacion()));
        given(service.updateRecomendacion(idRecomendacion,recomedacionToUpdate)).willReturn(recomedacionToUpdate);
        Recomendacion updated = service.updateRecomendacion(idRecomendacion,recomedacionToUpdate);

        ArgumentCaptor<Recomendacion> contactArgumentCaptor = ArgumentCaptor.forClass(Recomendacion.class);
        verify(repository).save(contactArgumentCaptor.capture());

        assertThat(updated.getComentarioRecomendacion()).isEqualTo("comentario actualizado");
    }

    @Test
    @DisplayName("Recomendacion not found to update")
    void updateRecomedacionWithException(){
        Integer idRecomendacion = 1;
        Recomendacion recomedacionToUpdate = new Recomendacion(1,"Naim Cambe", "comentario actualizado",  LocalDate.of(2021,04,23), "img/imgRecomendado.jpg");

        given(repository.findById(idRecomendacion)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateRecomendacion(idRecomendacion,recomedacionToUpdate)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Recomendacion deleted successfully")
    void deleteRecomedacion() {
        Integer idRecomendacion = 1;

        given(repository.findById(idRecomendacion)).willReturn(Optional.of(DatosDummy.getRecomendacion()));
        willDoNothing().given(repository).deleteById(idRecomendacion);
        service.deleteRecomendacion(idRecomendacion);

        verify(repository,times(1)).deleteById(idRecomendacion);

    }

    @Test
    @DisplayName("Recomendacion to delete not found")
    void deleteRecomedacionWithException(){
        Integer idRecomendacion = 1;

        given(repository.findById(idRecomendacion)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteRecomendacion(idRecomendacion)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Get all recomendaciones")
    void getAllRecomendaciones(){
        List<Recomendacion> recomendaciones= new ArrayList<>();
        recomendaciones.add(DatosDummy.getRecomendacion());
        recomendaciones.add(DatosDummy.getRecomendacion2());

        given(repository.findAll()).willReturn(recomendaciones);
        List<Recomendacion> recomendacionesList = service.getAllRecomendaciones();

        verify(repository,times(1)).findAll();
        assertThat(recomendacionesList.size()).isEqualTo(2);

    }

}