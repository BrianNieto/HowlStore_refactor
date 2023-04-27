package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.entity.Contact;
import com.asj.bootcamp.repository.ContactRepository;
import com.asj.bootcamp.service.ContactService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository repository;

    public ContactServiceImpl(ContactRepository repository) {
        this.repository = repository;
    }

    @Override
    public Contact createContact(Contact contact) {
        return repository.save(contact);
    }

    @Override
    public Contact getContact(Integer id) {
        Optional<Contact> optionalContact = repository.findById(id);
        if (optionalContact.isPresent()) {
            return optionalContact.get();
        }
        else {
            throw new RuntimeException("Contacto con id " + id + " no existe");
        }
    }

    @Override
    public Contact updateContact(Integer id, Contact tmp) {
        Contact contactUpdated;
        Optional<Contact> optionalContact = repository.findById(id);
        if (optionalContact.isPresent()){
            contactUpdated = optionalContact.get();
        }
        else {
            throw new RuntimeException("Contacto con id " + id + " no existe");
        }
        contactUpdated.setNombreContact(tmp.getNombreContact());
        contactUpdated.setApellidoContact(tmp.getApellidoContact());
        contactUpdated.setAsuntoContact(tmp.getAsuntoContact());
        contactUpdated.setMailContact(tmp.getMailContact());
        contactUpdated.setMensajeContact(tmp.getMensajeContact());

        return repository.save(contactUpdated);
    }

    @Override
    public void deleteContact(Integer id) {
        Optional<Contact> optionalContact = repository.findById(id);
        if (optionalContact.isPresent()) {
            repository.deleteById(id);
        }
        else {
            throw new RuntimeException("Contacto con id " + id + " no existe");
        }
    }
}