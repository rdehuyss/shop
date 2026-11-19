package org.jobrunr.shop.order.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Table("orders")
public class Order {

    @Id
    private UUID id;
    private final String customerId;
    @MappedCollection(idColumn = "order_id", keyColumn = "item_key")
    private final List<OrderItem> items;
    private final Instant createdAt;

    public Order(String customerId) {
        this(null, customerId, new ArrayList<>(), Instant.now());
    }

    @PersistenceCreator
    public Order(UUID id, String customerId, List<OrderItem> items, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.createdAt = createdAt;
    }


    public void addItem(String item) {
        items.add(new OrderItem(item));
    }

    public UUID getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public record OrderItem(String productId) {}

    public BigDecimal getOrderTotal() {
        return items.stream()
                .map(item -> new BigDecimal(ThreadLocalRandom.current().nextDouble(5, 10)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
