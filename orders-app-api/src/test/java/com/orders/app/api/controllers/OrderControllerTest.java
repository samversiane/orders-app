package com.orders.app.api.controllers;

import com.orders.app.api.dtos.OrderSummarizedDTO;
import com.orders.app.api.services.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllOrdersPaginated_ShouldReturnPageOfOrders() {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        List<OrderSummarizedDTO> orderList = new ArrayList<>();
        orderList.add(new OrderSummarizedDTO(1L, "Jhon Doe", "Company", 1, 200.0));
        Page<OrderSummarizedDTO> pagedResponse = new PageImpl<>(orderList, pageable, orderList.size());

        when(orderService.findAllOrdersPaginated(pageable)).thenReturn(pagedResponse);

        ResponseEntity<Page<OrderSummarizedDTO>> response = orderController.getAllOrdersPaginated(page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pagedResponse, response.getBody());
        verify(orderService, times(1)).findAllOrdersPaginated(pageable);
    }
}