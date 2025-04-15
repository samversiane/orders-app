package com.orders.app.api.dtos;

public record OrderSummarizedDTO(
        Long id,
        String customer,
        String supplier,
        Integer totalProducts,
        Double totalValue
) {
}
