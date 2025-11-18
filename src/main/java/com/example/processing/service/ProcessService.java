package com.example.processing.service;

import com.example.processing.data.Item;
import com.example.processing.data.ItemRepository;
import com.example.processing.dto.ItemDto;
import com.example.processing.dto.ProcessResponse;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProcessService {

    private final ItemRepository itemRepository;
    private static final Map<String, Double> CATEGORY_WEIGHTS = Map.of(
        "alpha", 1.0,
        "beta", 1.2,
        "gamma", 0.8
    );

    public ProcessService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ProcessResponse processItems(int topN) {
        List<Item> allItems = itemRepository.findAll();

        if (allItems.isEmpty()) {
            return new ProcessResponse(List.of(), 0, 0.0);
        }

        List<ItemDto> scoredItems = allItems.stream()
            .map(this::calculateScore)
            .sorted(Comparator.comparingDouble(ItemDto::score).reversed())
            .collect(Collectors.toList());

        double averageScore = scoredItems.stream()
            .mapToDouble(ItemDto::score)
            .average()
            .orElse(0.0);

        List<ItemDto> topItems = scoredItems.stream()
            .limit(topN)
            .collect(Collectors.toList());

        return new ProcessResponse(topItems, allItems.size(), averageScore);
    }

    private ItemDto calculateScore(Item item) {
        double weight = CATEGORY_WEIGHTS.getOrDefault(item.getCategory(), 1.0);
        double score = item.getItemValue() * weight;
        return ItemDto.from(item, score);
    }
}
