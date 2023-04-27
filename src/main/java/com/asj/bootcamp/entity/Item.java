package com.asj.bootcamp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idItem;
    @Column(nullable = false)
    String nombreItem;
    @Column(nullable = false)
    Integer precioItem;
    @Column(nullable = false)
    Integer stockItem;
    @Column(nullable = false)
    String estadoItem;
    @Column(nullable = false)
    String img1;
    @Column(nullable = false)
    String img2;
    @Column(nullable = false)
    String img3;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_categoria")
    Category category;

    public Item(String nombreItem, Integer precioItem, Integer stockItem, String estadoItem, String img1, String img2, String img3, Category category){
        super();
        this.nombreItem = nombreItem;
        this.precioItem = precioItem;
        this.stockItem = stockItem;
        this.estadoItem = estadoItem;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.category = category;
    }

}