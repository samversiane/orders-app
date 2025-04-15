package com.orders.app.api.services;

import com.orders.app.api.dtos.OrderItemDTO;
import com.orders.app.api.exceptions.BadRequestException;
import com.orders.app.api.mappers.OrderItemMapper;
import com.orders.app.api.repositories.OrderItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;

    public OrderItemService(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    public Page<OrderItemDTO> findAllItemsByOrderIdPaginated(Pageable pageable, Long orderId) {
        if (orderId == null) {
            throw new BadRequestException("Order ID cannot be null");
        }

        return orderItemRepository.findAllByOrderId(pageable, orderId).map(OrderItemMapper::entityToDto);
    }
}
