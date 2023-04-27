package com.asj.bootcamp.dto;

import com.asj.bootcamp.entity.Persona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserCompletoDTO {
    Integer idUser;
    String mail;
    String password;
    Persona persona;

}