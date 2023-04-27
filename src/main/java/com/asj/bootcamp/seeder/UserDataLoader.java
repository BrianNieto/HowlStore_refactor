package com.asj.bootcamp.seeder;

import com.asj.bootcamp.entity.Persona;
import com.asj.bootcamp.entity.User;
import com.asj.bootcamp.repository.PersonaRepository;
import com.asj.bootcamp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PersonaRepository personaRepository;

    @Override
    public void run(String... args) throws Exception {
        loadPersonaData();
        loadUserData();
    }


    private void loadPersonaData(){
        if (personaRepository.count() == 0) {
            Persona persona1 = new Persona("Matias", "Rodriguez");
            Persona persona2 = new Persona("Camila", "Silva");
            Persona persona3 = new Persona("Cristian", "Tristan");

            personaRepository.save(persona1);
            personaRepository.save(persona2);
            personaRepository.save(persona3);
        }
    }
    private void loadUserData() {
        if (userRepository.count() == 0) {
            Persona tmp1 = personaRepository.findById(1).get();
            Persona tmp2 = personaRepository.findById(2).get();
            Persona tmp3 = personaRepository.findById(3).get();

            User user1 = new User("test@admin.com", "1234", tmp1);
            User user2 = new User("test@gmail.com", "4567", tmp2);
            User user3 = new User("ssg@gmail.com", "90811", tmp3);

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);
        }
    }

}