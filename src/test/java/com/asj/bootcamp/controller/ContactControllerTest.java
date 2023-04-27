package com.asj.bootcamp.controller;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.dto.ContactDTO;
import com.asj.bootcamp.mapper.ContactMapper;
import com.asj.bootcamp.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private ContactService service;
    @MockBean
    private ContactMapper contactMapper;

    @Test
    @DisplayName("Contact created")
    void createContact() throws Exception {
        ContactDTO contactDTO = DatosDummy.getContactDTO();

        when(service.createContact(any())).thenReturn(DatosDummy.getContact());

        mockMvc.perform(
                        post("/contact")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(contactDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Get contact by id with status ACCEPTED")
    void getContact() throws Exception {
        Integer id = 1;

        when(service.getContact(any())).thenReturn(DatosDummy.getContact());
        mockMvc.perform(
                        get("/contact" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).getContact(any());
    }

    @Test
    @DisplayName("Get contact by id with status NOT FOUND")
    void getContactWithException() throws Exception {
        Integer id = 1;

        when(service.getContact(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(
                        get("/contact" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).getContact(any());
    }
    @Test
    @DisplayName("Update contact with status ACCEPTED")
    void updateContact() throws Exception {
        Integer id = 1;
        ContactDTO contactDTO = DatosDummy.getContactDTO();

        when(service.updateContact(any(),any())).thenReturn(DatosDummy.getContact());

        mockMvc.perform(
                        put("/contact" + "/{id}", id)
                                .content(mapper.writeValueAsString(contactDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(service,times(1)).updateContact(any(),any());
    }

    @Test
    @DisplayName("Update contact with status NOT FOUND")
    void updateContactWithException() throws Exception {
        Integer id = 1;
        ContactDTO contactDTO = DatosDummy.getContactDTO();

        when(service.updateContact(any(),any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        put("/contact" + "/{id}", id)
                                .content(mapper.writeValueAsString(contactDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service,times(1)).updateContact(any(),any());
    }

    @Test
    @DisplayName("Delete contact with status ACCEPTED")
    void deleteContact() throws Exception {
        Integer id = 1;

        doNothing().when(service).deleteContact(id);

        mockMvc.perform(
                        delete("/contact" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).deleteContact(any());
    }

    @Test
    @DisplayName("Delete contact with status NOT FOUND")
    void deleteContactWithException() throws Exception {
        Integer id = 1;

        doThrow(IllegalArgumentException.class).when(service).deleteContact(id);

        mockMvc.perform(
                        delete("/contact" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).deleteContact(any());
    }

}