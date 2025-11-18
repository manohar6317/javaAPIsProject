package com.example.processing.dto;

import java.util.List;

public record ProcessResponse(
    List<ItemDto> topItems,
    long count,
    double averageScore
) {}
