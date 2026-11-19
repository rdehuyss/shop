package org.jobrunr.shop.order;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.shop.order.repo.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class SalesReportService {

    private final OrderRepository orderRepository;

    public SalesReportService(OrderRepository orderRepository) {this.orderRepository = orderRepository;}

    @Recurring(id = "nightly-sales-report", cron = "0 2 * * *")
    @Job(name = "Nightly sales report")
    public void sendSalesReport() {
        // todo: create sales report
    }
}
