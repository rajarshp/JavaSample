package com.quartz.jobschedule;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob(ScriptExecutionJob.class)
                .withIdentity("scriptExecutionJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity("scriptExecutionTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")) // Runs every 30 seconds
                .build();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        return new SchedulerFactoryBean(); // Spring will handle the Quartz Scheduler creation
    }
}
