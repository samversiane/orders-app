package com.store.storeapi.dtos;

public record ProductDTO(
        Long id,
        String name,
        Double price
) {
}