package com.asj.bootcamp.entity;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categorias")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCategoria;
    @Column(nullable = false, unique = true)
    String nombreCategoria;

    public Category(String nombreCategoria){
        super();
        this.nombreCategoria = nombreCategoria;
    }

}