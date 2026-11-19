package org.jobrunr.shop.stock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Scheduled(cron = "0 0 * * * *")
    //@Recurring(id = "sync-stock", cron = "0 * * * *")
    public void syncStock() {
        System.out.println("Syncing stock every hour");
    }
}
