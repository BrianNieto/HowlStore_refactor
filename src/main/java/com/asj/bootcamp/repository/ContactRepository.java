package com.asj.bootcamp.repository;

import com.asj.bootcamp.entity.Contact;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}