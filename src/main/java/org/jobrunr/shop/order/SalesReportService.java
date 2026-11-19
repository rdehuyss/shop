package org.jobrunr.shop.order;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.shop.order.model.Order;
import org.jobrunr.shop.order.repo.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class SalesReportService {

    private final OrderRepository orderRepository;

    public SalesReportService(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Recurring(id = "nightly-sales-report", cron = "0 2 * * *")
    @Job(name = "Nightly sales report")
    public void sendSalesReport() {
        List<Order> orders = orderRepository.findByCreatedAtAfter(Instant.now().minus(1, ChronoUnit.DAYS).truncatedTo(ChronoUnit.DAYS));
        System.out.println("Total amount of sales: " + orders.stream().map(Order::getOrderTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
    }
}
