package com.asj.bootcamp.repository;

import com.asj.bootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByMailAndPassword(String mail, String password);

    Optional<User> findByMail(String mail);

}