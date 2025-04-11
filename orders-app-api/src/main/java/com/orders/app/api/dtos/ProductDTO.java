package com.orders.app.api.dtos;

public record ProductDTO(
        Long id,
        String name,
        Double price
) {
}