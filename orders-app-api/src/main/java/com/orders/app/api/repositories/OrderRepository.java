package com.orders.app.api.repositories;

import com.orders.app.api.dtos.OrderSummarizedDTO;
import com.orders.app.api.models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("""
                    SELECT new com.orders.app.api.dtos.OrderSummarizedDTO(
                        o.id,
                        c.name,
                        s.name,
                        CAST(SUM(i.quantity) AS int),
                        SUM(i.quantity * p.price)
                    )
                    FROM com.orders.app.api.models.Order o
                    JOIN o.customer c
                    JOIN o.supplier s
                    JOIN o.items i
                    JOIN i.product p
                    GROUP BY o.id, c.name, s.name
            """)
    Page<OrderSummarizedDTO> findAllOrdersSummarizedPaginated(Pageable pageable);
}
