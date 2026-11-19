package org.jobrunr.shop.order.repo;

import org.jobrunr.shop.order.model.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends CrudRepository<Order, UUID> {

    Order getById(UUID orderId);

    List<Order> findByCustomerId(String customerId);

    List<Order> findByCreatedAtAfter(Instant createdAtAfter);
}
