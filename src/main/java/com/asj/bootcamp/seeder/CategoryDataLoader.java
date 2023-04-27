package com.asj.bootcamp.seeder;

import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryDataLoader implements CommandLineRunner {

    @Autowired
    CategoryRepository repository;


    @Override
    public void run(String... args) throws Exception {
        loadCategoryData();
    }

    private void loadCategoryData(){
        if (repository.count() == 0){
            Category pistolas = new Category("Pistolas");
            Category smg = new Category("SMG");
            Category rifles = new Category("Rifles");
            Category shotguns = new Category("Shotguns");

            repository.save(pistolas);
            repository.save(smg);
            repository.save(shotguns);
            repository.save(rifles);
        }
    }

}