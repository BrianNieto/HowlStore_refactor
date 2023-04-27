package com.asj.bootcamp.seeder;

import com.asj.bootcamp.entity.Category;
import com.asj.bootcamp.entity.Item;
import com.asj.bootcamp.repository.CategoryRepository;
import com.asj.bootcamp.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ItemDataLoader implements CommandLineRunner {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadItemData();
    }

    private void loadItemData() {
        if (itemRepository.count() == 0){
            Category pistolas = categoryRepository.findByNombreCategoria("Pistolas").get();
            Category smg = categoryRepository.findByNombreCategoria("SMG").get();
            Category rifles = categoryRepository.findByNombreCategoria("Rifles").get();
            Category shotguns = categoryRepository.findByNombreCategoria("Shotguns").get();

            Item item1 = new Item("AK-47 | Leet Museo", 45000, 4,"Minimal Wear","assets/tienda/ak-47 leet museo(minimal wear)/1.png", "assets/tienda/ak-47 leet museo(minimal wear)/2.png", "assets/tienda/ak-47 leet museo(minimal wear)/3.jpg", rifles);
            Item item2 = new Item("Glock-18 | Gamma Doppler", 25000, 2,"Factory New","assets/tienda/glock-18 gamma doppler(factory new)/1.png", "assets/tienda/glock-18 gamma doppler(factory new)/2.jpg", "assets/tienda/glock-18 gamma doppler(factory new)/3.jpg", pistolas);
            Item item3 = new Item("AWP | Desert Hydra", 400000, 1,"Factory New","assets/tienda/awp desert hydra (factory new)/1.png", "assets/tienda/awp desert hydra (factory new)/2.jpg", "assets/tienda/awp desert hydra (factory new)/3.jpg", rifles);
            Item item4 = new Item("USP-S | Purple DDPAT", 5000, 7,"Factory New","assets/tienda/usp-s purple ddpat (factory new)/1.png", "assets/tienda/usp-s purple ddpat (factory new)/2.jpg", "assets/tienda/usp-s purple ddpat (factory new)/3.jpg", pistolas);
            Item item5 = new Item("SG553 | Hazard Pay", 300000, 3,"Factory New","assets/tienda/sg553 hazard pay (factory new)/1.png", "assets/tienda/sg553 hazard pay (factory new)/2.jpg", "assets/tienda/sg553 hazard pay (factory new)/3.jpg", rifles);
            Item item6 = new Item("Desert Eagle | Ocean Drive", 15000, 3,"Field Tested","assets/tienda/desert eagle ocean drive (field tested)/1.png", "assets/tienda/desert eagle ocean drive (field tested)/2.jpg", "assets/tienda/desert eagle ocean drive (field tested)/3.jpg", pistolas);
            Item item7 = new Item("M4A4 | The Coalition", 25600, 7,"Field Tested","assets/tienda/m4a4 the coalition (field tested)/1.png", "assets/tienda/m4a4 the coalition (field tested)/2.jpg", "assets/tienda/m4a4 the coalition (field tested)/3.jpg", rifles);
            Item item8 = new Item("MAC-10 | Toybox", 3000, 3,"Minimal Wear","assets/tienda/mac-10 toybox (minimal wear)/1.png", "assets/tienda/mac-10 toybox (minimal wear)/2.jpg", "assets/tienda/mac-10 toybox (minimal wear)/3.jpg", smg);
            Item item9 = new Item("MAG-7 | BI83 Spectrum", 2500, 13,"Factory New","assets/tienda/mag-7 bi83 spectrum (factory new)/1.png", "assets/tienda/mag-7 bi83 spectrum (factory new)/2.jpg", "assets/tienda/mag-7 bi83 spectrum (factory new)/3.jpg", shotguns);
            Item item10 = new Item("MP5-SD | Oxide Oasis", 18000, 2,"Field Tested","assets/tienda/mp5-sd oxide oasis (field tested)/1.png", "assets/tienda/mp5-sd oxide oasis (field tested)/2.jpg", "assets/tienda/mp5-sd oxide oasis (field tested)/3.jpg", smg);
            Item item11 = new Item("MP9 | Mount Fuji", 10000, 7,"Field Tested","assets/tienda/mp9 mount fuji (field tested)/1.png", "assets/tienda/mp9 mount fuji (field tested)/2.jpg", "assets/tienda/mp9 mount fuji (field tested)/3.jpg", smg);
            Item item12 = new Item("Nova | Red Quartz", 3000, 20,"Minimal Wear","assets/tienda/nova red quartz (minimal wear)/1.png", "assets/tienda/nova red quartz (minimal wear)/2.jpg", "assets/tienda/nova red quartz (minimal wear)/3.jpg", shotguns);
            Item item13 = new Item("USP-S | Whiteout", 35000, 6,"Well-Worn","assets/tienda/usp-s whiteout (well worn)/1.png", "assets/tienda/usp-s whiteout (well worn)/2.jpg", "assets/tienda/usp-s whiteout (well worn)/3.jpg", pistolas);
            Item item14 = new Item("XM1014 | Watchdog", 1800, 13,"Factory New","assets/tienda/xm1014 watchdog (factory new)/1.png", "assets/tienda/xm1014 watchdog (factory new)/2.jpg", "assets/tienda/xm1014 watchdog (factory new)/3.jpg", shotguns);


            itemRepository.save(item1);
            itemRepository.save(item2);
            itemRepository.save(item3);
            itemRepository.save(item4);
            itemRepository.save(item5);
            itemRepository.save(item6);
            itemRepository.save(item7);
            itemRepository.save(item8);
            itemRepository.save(item9);
            itemRepository.save(item10);
            itemRepository.save(item11);
            itemRepository.save(item12);
            itemRepository.save(item13);
            itemRepository.save(item14);

        }
    }

}