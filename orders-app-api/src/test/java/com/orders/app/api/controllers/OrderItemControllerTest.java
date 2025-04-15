package com.orders.app.api.controllers;

import com.orders.app.api.dtos.OrderItemDTO;
import com.orders.app.api.dtos.ProductDTO;
import com.orders.app.api.services.OrderItemService;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderItemControllerTest {

    @Mock
    private OrderItemService orderItemService;

    @InjectMocks
    private OrderItemController orderItemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllItemsByOrderIdPaginated_ShouldReturnPageOfItems() {
        Long orderId = 1L;
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        List<OrderItemDTO> itemList = new ArrayList<>();
        itemList.add(new OrderItemDTO(1L, new ProductDTO(1L, "stoven", 150.0), 1));
        Page<OrderItemDTO> pagedResponse = new PageImpl<>(itemList, pageable, itemList.size());

        when(orderItemService.findAllItemsByOrderIdPaginated(pageable, orderId)).thenReturn(pagedResponse);

        ResponseEntity<Page<OrderItemDTO>> response = orderItemController.findAllItemsByOrderIdPaginated(orderId, page, size);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(pagedResponse, response.getBody());
        verify(orderItemService, times(1)).findAllItemsByOrderIdPaginated(pageable, orderId);
    }
}