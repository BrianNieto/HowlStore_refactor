package com.asj.bootcamp.repository;

import com.asj.bootcamp.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {

}