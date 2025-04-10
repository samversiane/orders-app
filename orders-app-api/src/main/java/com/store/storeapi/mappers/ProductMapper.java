package com.store.storeapi.mappers;

import com.store.storeapi.dtos.ProductDTO;
import com.store.storeapi.models.Product;

public class ProductMapper {

    static ProductDTO entityToDto(Product entity) {
        return new ProductDTO(
                entity.getId(),
                entity.getName(),
                entity.getPrice()
        );
    }

    static Product DtoToEntity(ProductDTO dto) {
        return new Product(
                dto.id(),
                dto.name(),
                dto.price()
        );
    }
}
