package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Contact;
import com.asj.bootcamp.repository.ContactRepository;
import com.asj.bootcamp.service.ContactService;
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

@ContextConfiguration(classes = {ContactServiceImpl.class})
@SpringBootTest
class ContactServiceImplTest {

    @MockBean
    private ContactRepository repository;
    @Autowired
    private ContactService service;

    @Test
    @DisplayName("Contact created")
    void createContacto() {
        Contact contact = DatosDummy.getContact();
        service.createContact(contact);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(repository).save(contactArgumentCaptor.capture());

        Contact contactCaptor = contactArgumentCaptor.getValue();

        assertThat(contactCaptor).isEqualTo(contact);

        verify(repository).save(any());
    }

    @Test
    @DisplayName("Contact found")
    void getContacto() {
        Integer idContact = 1;

        when(this.repository.findById(idContact)).thenReturn(Optional.of(DatosDummy.getContact()));
        Contact contact = service.getContact(idContact);

        assertThat(contact.getIdContact()).isEqualTo(1);
    }

    @Test
    @DisplayName("Contact not found")
    void getContactWithException(){
        Integer idContact = 1;

        given(repository.findById(idContact)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.getContact(idContact)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Contact updated")
    void updateContacto() {
        Integer idContact = 1;
        Contact contactToUpdate = new Contact(1, "Naim", "Cambe", "naim@gmail.com","Dudas", "nuevo mensaje");

        given(repository.findById(idContact)).willReturn(Optional.of(DatosDummy.getContact()));
        given(service.updateContact(idContact,contactToUpdate)).willReturn(contactToUpdate);
        Contact updated = service.updateContact(idContact,contactToUpdate);

        ArgumentCaptor<Contact> contactArgumentCaptor = ArgumentCaptor.forClass(Contact.class);
        verify(repository).save(contactArgumentCaptor.capture());

        assertThat(updated.getMensajeContact()).isEqualTo("nuevo mensaje");
    }

    @Test
    @DisplayName("Contact not found to update")
    void updateCategoryWithException(){
        Integer idContact = 1;
        Contact contactToUpdate = new Contact(1, "Naim", "Cambe", "naim@gmail.com","Dudas", "nuevo mensaje");

        given(repository.findById(idContact)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.updateContact(idContact,contactToUpdate)).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Contact deleted successfully")
    void deleteContact() {
        Integer idContact = 1;

        given(repository.findById(idContact)).willReturn(Optional.of(DatosDummy.getContact()));
        willDoNothing().given(repository).deleteById(idContact);
        service.deleteContact(idContact);

        verify(repository,times(1)).deleteById(idContact);

    }

    @Test
    @DisplayName("Contact to delete not found")
    void deleteContactWithException(){
        Integer idContact = 1;

        given(repository.findById(idContact)).willReturn(Optional.empty());

        assertThatThrownBy(() -> service.deleteContact(idContact)).isInstanceOf(RuntimeException.class);
    }

}