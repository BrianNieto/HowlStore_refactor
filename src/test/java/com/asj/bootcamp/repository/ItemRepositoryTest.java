package com.asj.bootcamp.repository;

import com.asj.bootcamp.datos.DatosDummy;
import com.asj.bootcamp.entity.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ItemRepositoryTest {

    @Autowired
    private ItemRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryRepository.save(DatosDummy.getItem().getCategory());
        repository.save(DatosDummy.getItem());
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void findByNombreItemAndEstadoItem() {
        Optional<Item> optionalItem = this.repository.findByNombreItemAndEstadoItem("M9 Bayonet | Tiger Tooth","Factory New");

        assertThat(optionalItem.isPresent()).isTrue();
        assertThat(optionalItem.get().getNombreItem()).isEqualTo("M9 Bayonet | Tiger Tooth");
        assertThat(optionalItem.get().getEstadoItem()).isEqualTo("Factory New");
    }

    @Test
    void findAllByCategory(){
        List<Item> items = this.repository.findByIdCategory(DatosDummy.getItem().getCategory().getIdCategoria());

        assertThat(items.size()).isEqualTo(1);
        assertThat(items.get(0).getIdItem()).isEqualTo(1);

    }

}