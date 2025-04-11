package com.orders.app.api.controllers;

import com.orders.app.api.dtos.OrderDTO;
import com.orders.app.api.services.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderDTO>> getAllOrdersPaginated(Pageable pageable) {
        return ResponseEntity.ok(orderService.findAllOrdersPaginated(pageable));
    }
}
