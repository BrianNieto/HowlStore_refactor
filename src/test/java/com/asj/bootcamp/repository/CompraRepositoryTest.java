package com.asj.bootcamp.repository;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Compra;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CompraRepositoryTest {

    @Autowired
    private CompraRepository repository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ItemRepository itemRepository;

    @BeforeEach
    void setUp() {
        itemRepository.save(DatosDummy.getItem());
        userRepository.save(DatosDummy.getUser());
        repository.save(DatosDummy.getCompra());
        repository.save(DatosDummy.getCompra2());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findByIdUser() {
        Integer id = 1;
        List<Compra> compras = repository.findByIdUser(id);

        assertThat(compras.size()).isEqualTo(2);
        assertThat(compras.get(0).getUser().getIdUser()).isEqualTo(id);

    }

}