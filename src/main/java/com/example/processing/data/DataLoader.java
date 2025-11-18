package com.example.processing.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    private final ItemRepository itemRepository;

    public DataLoader(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        itemRepository.saveAll(Arrays.asList(
            new Item("Sword of Valor", "alpha", 100.0),
            new Item("Shield of Ages", "beta", 120.0),
            new Item("Elixir of Health", "gamma", 50.0),
            new Item("Dragon's Breath", "alpha", 250.0),
            new Item("Mystic Orb", "beta", 80.0),
            new Item("Goblin's Dagger", "gamma", 25.0)
        ));
    }
}

