package org.jobrunr.shop.stock;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Service;

@Service
public class StockService implements Job {

    public void syncStock() {
        System.out.println("Syncing stock every hour");
    }

    @Override
    public void execute(JobExecutionContext context) {
        syncStock();
    }
}
