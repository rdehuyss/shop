package org.jobrunr.shop.order.api;

import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.jobrunr.shop.order.OrderService;
import org.jobrunr.shop.order.model.Order;
import org.jobrunr.shop.order.repo.OrderRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class OrderController {


    private final OrderRepository orderRepository;
    private final JobScheduler jobScheduler;

    public OrderController(OrderRepository orderRepository, JobScheduler jobScheduler) {
        this.orderRepository = orderRepository;
        this.jobScheduler = jobScheduler;
    }

    @GetMapping("/add-to-order")
    public Order addToOrder(@RequestParam(required = false) UUID orderId, @RequestParam String product) {
        Order order = getOrder(orderId);
        order.addItem(product);
        orderRepository.save(order);
        return order;
    }

    @GetMapping("/confirm-order")
    public String confirmOrder(UUID orderId) {
        JobId jobId = jobScheduler.<OrderService>enqueue(o -> o.processOrder(orderId, JobContext.Null));
        return "Order confirmed: " + jobId.asUUID();
    }

    private Order getOrder(UUID orderId) {
        if (orderId == null) return new Order("customer-1");
        return orderRepository.getById(orderId);
    }
}
