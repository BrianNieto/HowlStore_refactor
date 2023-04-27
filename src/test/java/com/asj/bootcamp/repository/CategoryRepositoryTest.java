package com.asj.bootcamp.repository;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository repository;

    @BeforeEach
    void setUp() {
        this.repository.save(DatosDummy.getCategorySMG());
    }

    @AfterEach
    void tearDown() {
        this.repository.deleteAll();
    }

    @Test
    void findByNombreCategoria() {
        Optional<Category> optionalCategory = this.repository.findByNombreCategoria("SMG");

        assertThat(optionalCategory.isPresent()).isTrue();
        assertThat(optionalCategory.get().getNombreCategoria()).isEqualTo("SMG");
    }

}