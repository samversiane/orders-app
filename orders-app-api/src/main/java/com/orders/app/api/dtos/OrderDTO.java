package com.orders.app.api.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderDTO(
        Long id,
        CustomerDTO customerDTO,
        SupplierDTO supplierDTO,
        List<OrderItemDTO> items
) {
    @JsonProperty
    public int totalProducts() {
        return items.stream()
                .mapToInt(OrderItemDTO::quantity)
                .sum();
    }

    @JsonProperty
    public double totalValue() {
        return items.stream()
                .mapToDouble(i -> i.quantity() * i.productDTO().price())
                .sum();
    }
}
