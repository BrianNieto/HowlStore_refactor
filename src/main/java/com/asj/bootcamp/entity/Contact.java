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
@Table(name = "contactos")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idContact;
    @Column(nullable = false)
    String nombreContact;
    @Column(nullable = false)
    String apellidoContact;
    @Column(nullable = false)
    String mailContact;
    @Column(nullable = false)
    String asuntoContact;
    @Column(nullable = false)
    String mensajeContact;

}