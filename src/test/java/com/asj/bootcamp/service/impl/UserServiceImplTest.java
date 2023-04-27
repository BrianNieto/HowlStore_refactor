package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Persona;
import com.asj.bootcamp.entity.User;
import com.asj.bootcamp.repository.PersonaRepository;
import com.asj.bootcamp.repository.UserRepository;
import com.asj.bootcamp.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ContextConfiguration(classes = {UserServiceImpl.class})
@SpringBootTest
class UserServiceImplTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private PersonaRepository personaRepository;
    @Autowired
    private UserService service;

    @Test
    @DisplayName("User created")
    void createUser() {
        User user = DatosDummy.getUser();
        service.createUser(user);

        ArgumentCaptor<User> categoryArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(categoryArgumentCaptor.capture());

        User userCaptor = categoryArgumentCaptor.getValue();

        assertThat(userCaptor).isEqualTo(user);

        verify(userRepository).save(any());

    }

    @Test
    @DisplayName("User with exception")
    void createUserWithException(){
        User user = DatosDummy.getUser();

        given(userRepository.findByMail(user.getMail())).willReturn(Optional.of(user));

        assertThatThrownBy(() -> service.createUser(user)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("User found")
    void getUser() {
        Integer idUser = 1;

        when(userRepository.findById(idUser)).thenReturn(Optional.of(DatosDummy.getUser()));
        User user = service.getUser(idUser);

        assertThat(user.getIdUser()).isEqualTo(1);
    }

    @Test
    @DisplayName("User not found")
    void getUserWithException() {
        Integer idUser = 1;

        given(this.userRepository.findById(idUser)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.getUser(idUser)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("User updated")
    void updateUser() {
        Integer idUser = 1;
        Persona persona = new Persona(1,"Martin", "Pom");
        User userToUpdate = new User(1,"test@test.com", "4567", persona);

        given(userRepository.findById(idUser)).willReturn(Optional.of(DatosDummy.getUser()));
        given(personaRepository.findById(1)).willReturn(Optional.of(DatosDummy.getUser().getPersona()));
        given(service.updateUser(idUser,userToUpdate)).willReturn(userToUpdate);
        User userUpdated = service.updateUser(idUser, userToUpdate);

        ArgumentCaptor<User> categoryArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(categoryArgumentCaptor.capture());

        assertThat(userUpdated.getPassword()).isEqualTo("4567");
        assertThat(userUpdated.getPersona().getFirstname()).isEqualTo("Martin");
    }

    @Test
    @DisplayName("User with exception")
    void updateUserWithException(){
        Integer idUser = 1;
        Persona persona = new Persona(1,"Martin", "Pom");
        User userToUpdate = new User(1,"test@test.com", "4567", persona);

        given(userRepository.findById(idUser)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateUser(idUser,userToUpdate)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("User deleted")
    void deleteUser() {
        Integer idUser = 1;

        given(userRepository.findById(idUser)).willReturn(Optional.of(DatosDummy.getUser()));
        willDoNothing().given(userRepository).deleteById(idUser);
        willDoNothing().given(personaRepository).deleteById(DatosDummy.getUser().getPersona().getIdPersona());
        service.deleteUser(idUser);

        verify(userRepository,times(1)).deleteById(any());
        verify(personaRepository,times(1)).deleteById(any());

    }

    @Test
    @DisplayName("User to delete not found")
    void deleteUserWithException() {
        Integer idUser = 1;

        given(userRepository.findById(idUser)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteUser(idUser)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("User exist by mail and password")
    void validateUser() {
        User user = DatosDummy.getUser();

        given(userRepository.findByMailAndPassword(user.getMail(), user.getPassword())).willReturn(Optional.of(user));
        User userTmp = service.validateUser(user);

        assertThat(userTmp.getMail()).isEqualTo(user.getMail());
        assertThat(userTmp.getPassword()).isEqualTo(user.getPassword());

    }

    @Test
    @DisplayName("User not exist by mail and password")
    void validateUserWithException() {
        User user = DatosDummy.getUser();

        given(userRepository.findByMailAndPassword(user.getMail(),user.getPassword())).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.validateUser(user)).isInstanceOf(RuntimeException.class);

    }

    @Test
    @DisplayName("Email exist")
    void emailExist(){
        String mail = "test@test.com";

        given(userRepository.findByMail(mail)).willReturn(Optional.of(DatosDummy.getUser()));

        Boolean existUserByEmail = service.emailExist(mail);

        assertThat(existUserByEmail).isTrue();
    }

}