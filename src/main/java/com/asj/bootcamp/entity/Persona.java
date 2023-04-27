package com.asj.bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPersona;
    @Column(nullable = false)
    String firstname;
    @Column(nullable = false)
    String lastname;

    public Persona(String firstname, String lastname){
        super();
        this.firstname = firstname;
        this.lastname = lastname;
    }

}