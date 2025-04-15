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
            // Criando clientes
            List<Customer> customers = List.of(
                    customerRepository.save(new Customer("John Doe")),
                    customerRepository.save(new Customer("Jane Smith")),
                    customerRepository.save(new Customer("Alice Johnson"))
            );
            log.info("Preloaded Customers {}", customers);

            // Criando fornecedores
            List<Supplier> suppliers = List.of(
                    supplierRepository.save(new Supplier("Acme Supplies")),
                    supplierRepository.save(new Supplier("Best Goods Ltd.")),
                    supplierRepository.save(new Supplier("Global Suppliers Inc."))
            );
            log.info("Preloaded Suppliers {}", suppliers);

            // Criando 20 produtos
            List<Product> products = new ArrayList<>();
            for (int i = 1; i <= 20; i++) {
                Product product = productRepository.save(new Product("Product " + i, 10.0 * i));
                products.add(product);
                log.info("Preloading Product {}", product);
            }

            // Criando 30 pedidos
            for (int i = 1; i <= 30; i++) {
                // Alternando clientes e fornecedores
                Customer customer = customers.get(i % customers.size());
                Supplier supplier = suppliers.get(i % suppliers.size());

                // Criando pedido
                Order order = orderRepository.save(new Order(customer, supplier, new ArrayList<>()));
                log.info("Preloading Order {} without items: {}", i, order);

                // Criando entre 5 a 20 itens aleatórios para o pedido
                List<OrderItem> orderItems = new ArrayList<>();
                int itemsCount = 5 + (i % 16); // Quantidade de itens (5 a 20, pois 5 + resto de 16 = até 20)
                for (int j = 0; j < itemsCount; j++) {
                    Product product = products.get((i + j) % products.size()); // Seleciona produtos rotativamente
                    int quantity = 1 + (j % 10); // Quantidade de cada item (1 a 10)
                    OrderItem orderItem = orderItemRepository.save(new OrderItem(product, order, quantity));
                    orderItems.add(orderItem);
                    log.info("Preloading Item for Order {} -> {} (Qty: {})", i, product.getName(), quantity);
                }

                // Associando itens ao pedido
                order.setItems(orderItems);
                orderRepository.save(order);
                log.info("Final Order {} with items: {}", i, order);
            }
        };
    }
}