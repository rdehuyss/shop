package org.jobrunr.shop.stock;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

@Configuration
public class StockQuartzConfig {

    @Bean
    public JobDetail syncStockJobDetail() {
        return newJob(StockService.class)
                .withIdentity("sync-stock")
                .withDescription("Sync stock every hour")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger syncStockTrigger(JobDetail syncStockJobDetail) {
        return newTrigger()
                .forJob(syncStockJobDetail)
                .withIdentity("sync-stock-trigger")
                .withSchedule(cronSchedule("0 0 * * * ?"))
                .build();
    }
}
