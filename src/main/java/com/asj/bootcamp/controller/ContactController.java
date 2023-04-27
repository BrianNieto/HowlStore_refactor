package com.asj.bootcamp.controller;

import com.asj.bootcamp.dto.ContactDTO;
import com.asj.bootcamp.entity.Contact;
import com.asj.bootcamp.mapper.ContactMapper;
import com.asj.bootcamp.service.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/contact")
public class ContactController {

    private final ContactService service;
    private final ContactMapper mapper;


    public ContactController(ContactService service, ContactMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<?> createContact(@RequestBody ContactDTO contactDTO){
        Contact contact = mapper.contactDTOToContactEntity(contactDTO);
        service.createContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContacto(@PathVariable Integer id){
        try {
            Contact contact =  service.getContact(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mapper.contactEntityToContactDTO(contact));
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrado");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateContacto(@PathVariable Integer id, @RequestBody ContactDTO contactDTO){
        try {
            Contact tmp = mapper.contactDTOToContactEntity(contactDTO);
            ContactDTO updated = mapper.contactEntityToContactDTO(service.updateContact(id, tmp));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contacto no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContacto(@PathVariable Integer id){
        try {
            service.deleteContact(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception ex){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Recomendacion no encontrada");
        }
    }
}