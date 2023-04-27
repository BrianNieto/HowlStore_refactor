package com.asj.bootcamp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ContactDTO {

    Integer idContact;
    String nombreContact;
    String apellidoContact;
    String mailContact;
    String asuntoContact;
    String mensajeContact;

}