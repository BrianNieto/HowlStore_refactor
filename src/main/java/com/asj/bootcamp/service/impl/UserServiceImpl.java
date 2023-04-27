package com.asj.bootcamp.service.impl;

import com.asj.bootcamp.entity.Persona;
import com.asj.bootcamp.entity.User;
import com.asj.bootcamp.repository.PersonaRepository;
import com.asj.bootcamp.repository.UserRepository;
import com.asj.bootcamp.service.UserService;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PersonaRepository personaRepository;

    public UserServiceImpl(UserRepository userRepository, PersonaRepository personaRepository) {
        this.userRepository = userRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    public User createUser(User user) {
        if (emailExist(user.getMail())) {
            throw new RuntimeException(String.format("Usuario con mail %s ya registrado", user.getMail()));
        }
        Persona tmp = personaRepository.save(user.getPersona());
        user.setPersona(tmp);

        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        else {
            throw new RuntimeException("Usuario con id " + id + " no existe");
        }
    }

    @Override
    public User updateUser(Integer id, User user) {
        User userUpdated;
        Persona personaUpdated;

        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userUpdated = optionalUser.get();
        }
        else {
            throw new RuntimeException("Usuario con id " + id + " no existe");
        }

        Optional<Persona> optionalPersona = personaRepository.findById(userUpdated.getPersona().getIdPersona());

        personaUpdated = optionalPersona.get();
        personaUpdated.setFirstname(user.getPersona().getFirstname());
        personaUpdated.setLastname(user.getPersona().getLastname());

        personaRepository.save(personaUpdated);

        userUpdated.setPersona(personaUpdated);
        userUpdated.setPassword(user.getPassword());

        return userRepository.save(userUpdated);
    }

    @Override
    public void deleteUser(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            userRepository.deleteById(id);
            personaRepository.deleteById(optionalUser.get().getPersona().getIdPersona());
        }
        else {
            throw new RuntimeException("Usuario con id " + id + " no existe");
        }
    }

    @Override
    public User validateUser(User user) {
        Optional<User> tmp = userRepository.findByMailAndPassword(user.getMail(), user.getPassword());
        if (tmp.isPresent()){
            return tmp.get();
        }
        else {
            throw new RuntimeException("mail o password incorrectos");
        }
    }

    @Override
    public boolean emailExist(String email) {
        return userRepository.findByMail(email).isPresent();
    }

}