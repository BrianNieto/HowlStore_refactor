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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idUser;
    @Column(nullable = false, unique = true)
    String mail;
    @Column(nullable = false)
    String password;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idPersona")
    Persona persona;

    public User(String mail, String password, Persona persona){
        super();
        this.mail = mail;
        this.password = password;
        this.persona = persona;
    }

}