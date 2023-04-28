package com.asj.bootcamp.datos;

import com.asj.bootcamp.dto.*;
import com.asj.bootcamp.entity.*;
import com.asj.bootcamp.model.entity.Category;

import java.time.LocalDate;

public class DatosDummy {

    //CATEGORIAS
    public static Category getCategorySMG(){
        return new Category(1,"SMG");
    }
    public static Category getCategoryRifles(){
        return new Category(2,"Rifles");
    }

    public static CategoryDTO getCategorySMGDTO(){
        return new CategoryDTO(1,"SMG");
    }

    //USUARIO
    public static User getUser(){
        Persona tmp = new Persona(1,"Pipo", "Pom");
        return new User(1,"test@test.com", "1234", tmp);
    }

    public static UserCompletoDTO getUserCompletoDTO(){
        Persona tmp = new Persona(1,"Pipo", "Pom");
        return new UserCompletoDTO(1,"test@test.com", "1234", tmp);
    }

    public static UserLoginDTO getUserLoginDTO(){
        return new UserLoginDTO("test@test.com","1234");
    }

    //ITEMS
    public static Item getItem(){
        Category tmp = new Category(1,"Cuchillo");
        return new Item(1,"M9 Bayonet | Tiger Tooth", 30000, 5, "Factory New", "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", tmp);
    }

    public static Item getItem2(){
        Category tmp = new Category(2,"Pistolas");
        return new Item(2,"Glock-18 | Gamma Doppler", 15000, 3, "Factory New", "img/img4.jpg", "img/img5.jpg", "img/img6.jpg", tmp);
    }

    public static Item getItem3(){
        Category tmp = new Category(2,"Pistolas");
        return new Item(2,"USP-S | Pink DDPAT", 3000, 10, "Factory New", "img/img7.jpg", "img/img8.jpg", "img/img9.jpg", tmp);
    }

    public static ItemCompletoDTO getItemCompletoDTO(){
        Category tmp = new Category(1,"Cuchillo");
        return new ItemCompletoDTO(1,"M9 Bayonet | Tiger Tooth", 30000, 5, "Factory New", "img/img1.jpg", "img/img2.jpg", "img/img3.jpg", tmp);
    }

    //CONTACTOS
    public static Contact getContact(){
        return new Contact(1, "Naim", "Cambe", "naim@gmail.com","Dudas", "zz z z a dawd ad a");
    }

    public static ContactDTO getContactDTO(){
        return new ContactDTO(1, "Naim", "Cambe", "naim@gmail.com","Dudas", "zz z z a dawd ad a");
    }

    //RECOMENDACIONES
    public static Recomendacion getRecomendacion(){
        return new Recomendacion(1,"Naim Cambe", "zz z z a dawd ad a",  LocalDate.of(2021,04,23), "img/imgRecomendado.jpg");
    }

    public static Recomendacion getRecomendacion2(){
        return new Recomendacion(2,"Roberto Miwaq", "otro más?",  LocalDate.of(2023,02,14), "img/imgRecomendado2.jpg");
    }

    public static RecomendacionDTO getRecomendacionDTO(){
        return new RecomendacionDTO(1,"Naim Cambe", "zz z z a dawd ad a",  LocalDate.of(2021,04,23), "img/imgRecomendado.jpg");
    }

    //COMPRA
    public static Compra getCompra(){
        return new Compra(1,"Realizado", LocalDate.of(2022,03,12), "REALIZADO", getItem(),getUser());
    }

    public static Compra getCompra2(){
        return new Compra(2,"Realizado", LocalDate.of(2022,03,12), "REALIZADO", getItem(),getUser());
    }

}