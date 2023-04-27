package com.asj.bootcamp.service;

import com.asj.bootcamp.entity.Persona;
import com.asj.bootcamp.entity.User;
import com.asj.bootcamp.exception.NotFoundException;

public interface UserService {
    User getUser(Integer id);

    User createUser(User user);

    boolean emailExist(String email);

    User updateUser(Integer id, User tmp);

    void deleteUser(Integer id);

    User validateUser(User user);
}