package com.orders.app.api.dtos;

public record OrderItemDTO(
        Long id,
        ProductDTO productDTO,
        Integer quantity
) {
}
