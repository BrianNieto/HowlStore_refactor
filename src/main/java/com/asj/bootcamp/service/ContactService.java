package com.asj.bootcamp.service;

import com.asj.bootcamp.entity.Contact;

public interface ContactService {

    Contact createContact(Contact contact);

    Contact getContact(Integer id);

    Contact updateContact(Integer id, Contact tmp);

    void deleteContact(Integer id);
}