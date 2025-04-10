package com.store.storeapi.dtos;

public record OrderItemDTO(
        Long id,
        ProductDTO productDTO,
        Integer quantity
) {
}
