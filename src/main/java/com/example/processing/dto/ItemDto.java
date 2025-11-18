package com.example.processing.dto;

import com.example.processing.data.Item;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ItemDto(
    Long id,
    String name,
    String category,
    @JsonProperty("value")
    Double itemValue,
    Double score
) {
    /**
     * Factory method to create an ItemDto from an Item entity and a calculated score.
     */
    public static ItemDto from(Item item, Double score) {
        return new ItemDto(item.getId(), item.getName(), item.getCategory(), item.getItemValue(), score);
    }
}
