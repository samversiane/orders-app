package com.store.storeapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;
import java.util.Objects;

@Entity(name = "ORDER_TB")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Supplier supplier;

    @OneToMany
    private List<OrderItem> items;

    public Order() {
    }

    public Order(
            Long id,
            Customer customer,
            Supplier supplier,
            List<OrderItem> items
    ) {
        this.id = id;
        this.customer = customer;
        this.supplier = supplier;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Order) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.customer, that.customer) &&
                Objects.equals(this.supplier, that.supplier) &&
                Objects.equals(this.items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer, supplier, items);
    }

    @Override
    public String toString() {
        return "Order[id=%s, customer=%s, supplier=%s, items=%s]".formatted(id, customer, supplier, items);
    }

}
