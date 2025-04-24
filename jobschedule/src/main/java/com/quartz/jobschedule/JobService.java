package com.quartz.jobschedule;

import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final Scheduler scheduler;

    @Autowired
    public JobService(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void init() throws SchedulerException {
        scheduleJob();
    }

    public void scheduleJob() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(ScriptExecutionJob.class)
                .withIdentity("scriptExecutionJob")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("scriptExecutionTrigger")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(60).repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
