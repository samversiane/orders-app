package com.orders.app.api.services;

import com.orders.app.api.dtos.OrderSummarizedDTO;
import com.orders.app.api.exceptions.OrderProcessingException;
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

    public Page<OrderSummarizedDTO> findAllOrdersPaginated(Pageable pageable) {
        try {
            return orderRepository.findAllOrdersSummarizedPaginated(pageable);
        } catch (Exception e) {
            throw new OrderProcessingException("Error fetching orders: %s".formatted(e.getMessage()));
        }
    }
}
