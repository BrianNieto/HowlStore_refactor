package com.asj.bootcamp.controller;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.dto.RecomendacionDTO;
import com.asj.bootcamp.entity.Recomendacion;
import com.asj.bootcamp.mapper.RecomendacionMapper;
import com.asj.bootcamp.service.RecomendacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecomendacionController.class)
class RecomendacionControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private RecomendacionService service;
    @MockBean
    private RecomendacionMapper recomendacionMapper;

    @Test
    @DisplayName("Recomendacion created")
    void createRecomedacion() throws Exception {
        RecomendacionDTO recomendacionDTO = DatosDummy.getRecomendacionDTO();

        when(service.createRecomendacion(any())).thenReturn(DatosDummy.getRecomendacion());

        mockMvc.perform(
                post("/recomendaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(recomendacionDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Get recomendacion by id with status ACCEPTED")
    void getRecomendacion() throws Exception {
        Integer id = 1;

        when(service.getRecomendacion(any())).thenReturn(DatosDummy.getRecomendacion());
        mockMvc.perform(
                    get("/recomendaciones" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).getRecomendacion(any());
    }

    @Test
    @DisplayName("Get recomendacion by id with status NOT FOUND")
    void getRecomendacionWithException() throws Exception {
        Integer id = 1;

        when(service.getRecomendacion(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(
                        get("/recomendaciones" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).getRecomendacion(any());
    }
    @Test
    @DisplayName("Update recomendacion with status ACCEPTED")
    void updateRecomendacion() throws Exception {
        Integer id = 1;
        RecomendacionDTO recomendacionDTO = DatosDummy.getRecomendacionDTO();

        when(service.updateRecomendacion(any(),any())).thenReturn(DatosDummy.getRecomendacion());

        mockMvc.perform(
                put("/recomendaciones" + "/{id}", id)
                        .content(mapper.writeValueAsString(recomendacionDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(service,times(1)).updateRecomendacion(any(),any());
    }

    @Test
    @DisplayName("Update recomendacion with status NOT FOUND")
    void updateRecomendacionWithException() throws Exception {
        Integer id = 1;
        RecomendacionDTO recomendacionDTO = DatosDummy.getRecomendacionDTO();

        when(service.updateRecomendacion(any(),any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        put("/recomendaciones" + "/{id}", id)
                                .content(mapper.writeValueAsString(recomendacionDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service,times(1)).updateRecomendacion(any(),any());
    }

    @Test
    @DisplayName("Delete recomendacion with status ACCEPTED")
    void deleteRecomendacion() throws Exception {
        Integer id = 1;

        doNothing().when(service).deleteRecomendacion(id);

        mockMvc.perform(
                    delete("/recomendaciones" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).deleteRecomendacion(any());
    }

    @Test
    @DisplayName("Delete recomendacion with status NOT FOUND")
    void deleteRecomendacionWithException() throws Exception {
        Integer id = 1;

        doThrow(IllegalArgumentException.class).when(service).deleteRecomendacion(id);

        mockMvc.perform(
                        delete("/recomendaciones" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).deleteRecomendacion(any());
    }

    @Test
    void getAllRecomendaciones() throws Exception {
        List<Recomendacion> recomendaciones = new ArrayList<>();
        recomendaciones.add(DatosDummy.getRecomendacion());
        recomendaciones.add(DatosDummy.getRecomendacion2());

        when(service.getAllRecomendaciones()).thenReturn(recomendaciones);

        mockMvc.perform(
                get("/recomendaciones"))
                .andExpect(status().isOk());
        verify(service,times(1)).getAllRecomendaciones();
    }

}