package com.orders.app.api.services;

import com.orders.app.api.dtos.OrderSummarizedDTO;
import com.orders.app.api.repositories.OrderRepository;
import com.orders.app.api.exceptions.OrderProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllOrdersPaginated_ShouldReturnOrders_WhenSuccessful() {
        Pageable pageable = PageRequest.of(0, 5);
        List<OrderSummarizedDTO> orderList = new ArrayList<>();
        orderList.add(new OrderSummarizedDTO(1L, "Jhon Doe", "Company", 1, 200.0));
        Page<OrderSummarizedDTO> pagedResponse = new PageImpl<>(orderList, pageable, orderList.size());

        when(orderRepository.findAllOrdersSummarizedPaginated(pageable)).thenReturn(pagedResponse);

        Page<OrderSummarizedDTO> result = orderService.findAllOrdersPaginated(pageable);

        assertNotNull(result);
        assertEquals(pagedResponse, result);
        verify(orderRepository, times(1)).findAllOrdersSummarizedPaginated(pageable);
    }

    @Test
    void findAllOrdersPaginated_ShouldThrowException_WhenErrorOccurs() {
        Pageable pageable = PageRequest.of(0, 5);
        String errorMsg = "Database connection error";

        when(orderRepository.findAllOrdersSummarizedPaginated(pageable))
                .thenThrow(new RuntimeException(errorMsg));

        OrderProcessingException exception = assertThrows(OrderProcessingException.class,
                () -> orderService.findAllOrdersPaginated(pageable));

        assertEquals("Error fetching orders: %s".formatted(errorMsg), exception.getMessage());
        verify(orderRepository, times(1)).findAllOrdersSummarizedPaginated(pageable);
    }
}