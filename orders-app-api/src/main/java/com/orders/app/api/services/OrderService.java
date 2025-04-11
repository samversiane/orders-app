package com.orders.app.api.services;

import com.orders.app.api.dtos.OrderDTO;
import com.orders.app.api.mappers.OrderMapper;
import com.orders.app.api.repositories.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Page<OrderDTO> findAllOrdersPaginated(Pageable pageable) {
        return orderRepository.findAll(pageable).map(OrderMapper::entityToDto);
    }
}
