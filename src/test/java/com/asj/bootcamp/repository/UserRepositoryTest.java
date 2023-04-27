package com.asj.bootcamp.repository;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        repository.save(DatosDummy.getUser());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findByMailAndPassword() {
        Optional<User> optionalUser = this.repository.findByMailAndPassword("test@test.com","1234");

        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser.get().getMail()).isEqualTo("test@test.com");
        assertThat(optionalUser.get().getPassword()).isEqualTo("1234");

    }

    @Test
    void findByMail() {
        Optional<User> optionalUser = this.repository.findByMail("test@test.com");

        assertThat(optionalUser.isPresent()).isTrue();
        assertThat(optionalUser.get().getMail()).isEqualTo("test@test.com");

    }
}