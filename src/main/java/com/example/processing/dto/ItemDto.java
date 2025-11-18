package com.example.processing.dto;

import com.example.processing.data.Item;

public record ItemDto(
    Long id,
    String name,
    String category,
    Double value,
    Double score
) {
    /**
     * Factory method to create an ItemDto from an Item entity and a calculated score.
     */
    public static ItemDto from(Item item, Double score) {
        return new ItemDto(item.getId(), item.getName(), item.getCategory(), item.getValue(), score);
    }
}

