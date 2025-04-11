package com.orders.app.api.configs;

import com.orders.app.api.models.Customer;
import com.orders.app.api.models.Order;
import com.orders.app.api.models.OrderItem;
import com.orders.app.api.models.Product;
import com.orders.app.api.models.Supplier;
import com.orders.app.api.repositories.CustomerRepository;
import com.orders.app.api.repositories.OrderItemRepository;
import com.orders.app.api.repositories.OrderRepository;
import com.orders.app.api.repositories.ProductRepository;
import com.orders.app.api.repositories.SupplierRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(
            CustomerRepository customerRepository,
            SupplierRepository supplierRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository
    ) {
        return args -> {
            Customer customer = customerRepository.save(new Customer("John Doe"));
            log.info("Preloading {}", customer);

            Supplier supplier = supplierRepository.save(new Supplier("Acme Supplies"));
            log.info("Preloading {}", supplier);

            Product rod = productRepository.save(new Product("Fishing Rod", 100.0));
            Product bait = productRepository.save(new Product("Bait Pack", 20.0));
            log.info("Preloading {}", rod);
            log.info("Preloading {}", bait);

            Order order = orderRepository.save(new Order(customer, supplier, new ArrayList<>()));
            log.info("Preloading Order without items: {}", order);

            OrderItem item1 = orderItemRepository.save(new OrderItem(rod, order, 1));
            OrderItem item2 = orderItemRepository.save(new OrderItem(bait, order, 3));

            List<OrderItem> orderItems = List.of(item1, item2);
            order.setItems(orderItems);
            orderRepository.save(order);

            log.info("Final Order with items: {}", order);
        };
    }
}
