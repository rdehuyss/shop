package org.jobrunr.shop.order;

import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    private final JobScheduler jobScheduler;

    public OrderService(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    public void processOrder(UUID orderId, JobContext jobContext) {
        // todo: process the order
    }

    protected void createOrderPickingInstruction(UUID orderId, JobContext jobContext) {
        System.out.println("creating-order: " + orderId);
    }

    protected void sendOrderConfirmation(UUID orderId, JobContext jobContext) {
        System.out.println("sending-order-confirmation: " + orderId);
    }

    protected void createAndSendInvoice(UUID orderId, JobContext jobContext) {
        System.out.println("sending-invoice: " + orderId);
    }

    protected void sendDiscountEmail(UUID orderId) {
        System.out.println("Sending discount email: " + orderId);
    }
}
