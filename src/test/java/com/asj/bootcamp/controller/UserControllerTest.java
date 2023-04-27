package com.asj.bootcamp.controller;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.dto.UserCompletoDTO;
import com.asj.bootcamp.dto.UserLoginDTO;
import com.asj.bootcamp.mapper.UserMapper;
import com.asj.bootcamp.service.UserService;
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

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserService service;
    @MockBean
    private UserMapper userMapper;

    @Test
    @DisplayName("User created with status CREATED")
    void createUser() throws Exception {
        UserCompletoDTO userCompletoDTO = DatosDummy.getUserCompletoDTO();

        when(service.emailExist(any())).thenReturn(false);
        when(service.createUser(DatosDummy.getUser())).thenReturn(DatosDummy.getUser());

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(userCompletoDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("User created with status CONFLICT")
    void createUserWithException() throws Exception {
        UserCompletoDTO userCompletoDTO = DatosDummy.getUserCompletoDTO();

        when(service.createUser(any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        post("/users")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(userCompletoDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    @DisplayName("Get user by id with status ACCEPTED")
    void getUser() throws Exception {
        Integer id = 1;

        when(service.getUser(any())).thenReturn(DatosDummy.getUser());
        mockMvc.perform(
                        get("/users" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).getUser(any());

    }

    @Test
    @DisplayName("Get user by id with status NOT FOUND")
    void getUserWithException() throws Exception {
        Integer id = 1;

        when(service.getUser(any())).thenThrow(IllegalArgumentException.class);
        mockMvc.perform(
                        get("/users" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).getUser(any());

    }
    @Test
    @DisplayName("Update user with status ACCEPTED")
    void updateUser() throws Exception {
        Integer id = 1;
        UserCompletoDTO userCompletoDTO = DatosDummy.getUserCompletoDTO();

        when(service.updateUser(any(),any())).thenReturn(DatosDummy.getUser());

        mockMvc.perform(
                        put("/users" + "/{id}", id)
                                .content(mapper.writeValueAsString(userCompletoDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());

        verify(service,times(1)).updateUser(any(),any());
    }

    @Test
    @DisplayName("Update user with status NOT FOUND")
    void updateUserWithException() throws Exception {
        Integer id = 1;
        UserCompletoDTO userCompletoDTO = DatosDummy.getUserCompletoDTO();

        when(service.updateUser(any(),any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        put("/users" + "/{id}", id)
                                .content(mapper.writeValueAsString(userCompletoDTO))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(service,times(1)).updateUser(any(),any());
    }

    @Test
    @DisplayName("Delete user with status ACCEPTED")
    void deleteUser() throws Exception {
        Integer id = 1;

        doNothing().when(service).deleteUser(id);

        mockMvc.perform(
                        delete("/users" + "/{id}", id))
                .andExpect(status().isAccepted());

        verify(service,times(1)).deleteUser(any());
    }

    @Test
    @DisplayName("Delete user with status NOT FOUND")
    void deleteUserWithException() throws Exception {
        Integer id = 1;

        doThrow(IllegalArgumentException.class).when(service).deleteUser(id);

        mockMvc.perform(
                        delete("/users" + "/{id}", id))
                .andExpect(status().isNotFound());

        verify(service,times(1)).deleteUser(any());
    }

    @Test
    @DisplayName("Validate user with status OK")
    void validateUser() throws Exception {
        UserLoginDTO userLoginDTO = DatosDummy.getUserLoginDTO();
        when(service.validateUser(any())).thenReturn(DatosDummy.getUser());

        mockMvc.perform(
                post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(userLoginDTO)))
                .andExpect(status().isOk());

        verify(service,times(1)).validateUser(any());
    }

    @Test
    @DisplayName("Validate user with status NOT FOUND")
    void validateUserWithException() throws Exception {
        UserLoginDTO userLoginDTO = DatosDummy.getUserLoginDTO();
        when(service.validateUser(any())).thenThrow(IllegalArgumentException.class);

        mockMvc.perform(
                        post("/users/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(this.mapper.writeValueAsString(userLoginDTO)))
                .andExpect(status().isNotFound());

        verify(service,times(1)).validateUser(any());
    }

}