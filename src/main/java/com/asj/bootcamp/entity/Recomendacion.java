package com.asj.bootcamp.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recomendaciones")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idRecomendacion;
    @Column(nullable = false)
    String nombreRecomendacion;
    @Column(nullable = false)
    String comentarioRecomendacion;
    @Column(nullable = false)
    LocalDate fechaRecomendacion;
    @Column(nullable = false)
    String imgRecomendacion;

    public Recomendacion(String nombreRecomendacion, String comentarioRecomendacion, LocalDate fechaRecomendacion, String imgRecomendacion){
        super();
        this.nombreRecomendacion = nombreRecomendacion;
        this.comentarioRecomendacion = comentarioRecomendacion;
        this.fechaRecomendacion = fechaRecomendacion;
        this.imgRecomendacion = imgRecomendacion;
    }

}