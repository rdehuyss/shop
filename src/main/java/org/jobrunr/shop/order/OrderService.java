package org.jobrunr.shop.order;

import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
public class OrderService {

    private final JobScheduler jobScheduler;

    public OrderService(JobScheduler jobScheduler) {
        this.jobScheduler = jobScheduler;
    }

    public void processOrder(UUID orderId, JobContext jobContext) {
        jobContext.runStepOnce("order-picking", () -> createOrderPickingInstruction(orderId, jobContext));
        jobContext.runStepOnce("order-confirmation", () -> sendOrderConfirmation(orderId, jobContext));
        jobContext.runStepOnce("invoice", () -> createAndSendInvoice(orderId, jobContext));
        
        jobScheduler.schedule(Instant.now().plus(36, ChronoUnit.HOURS), () -> sendDiscountEmail(orderId));
    }

    public void sendDiscountEmail(UUID orderId) {
        System.out.println("Sending discount email: " + orderId);
    }

    protected void createOrderPickingInstruction(UUID orderId, JobContext jobContext) {
        System.out.println("creating-order: " + orderId);
    }

    protected void sendOrderConfirmation(UUID orderId, JobContext jobContext) {
        if (jobContext.currentRetry() < 3) throw new IllegalStateException("Mail Server is down");
        System.out.println("sending-order-confirmation: " + orderId);
    }

    protected void createAndSendInvoice(UUID orderId, JobContext jobContext) {
        System.out.println("sending-invoice: " + orderId);
    }
}
