package com.orders.app.api.dtos;

public record OrderItemDTO(
        Long id,
        ProductDTO product,
        Integer quantity
) {
}
