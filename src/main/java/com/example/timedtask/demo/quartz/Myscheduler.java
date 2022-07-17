package com.example.timedtask.demo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 还是几个问题
 * 对谁进行定时任务，基本数据类型还是引用数据类型
 * 返回是什么，要执行什么定时任务
 */
public class Myscheduler  {

    /**
     * JobDataMap实现了JDK的Map接口，可以以Key-Value的形式存储数据。
     * JobDetail、Trigger都可以使用JobDataMap来设置一些参数或信息，
     * Job执行execute()方法的时候，JobExecutionContext可以获取到JobExecutionContext中的信息
     */
    public static void main(String[] args) throws  Exception{
        // 1、创建调度器Scheduler
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class)
                .withIdentity("job1", "group1").build();
        // 3、构建Trigger实例,每隔1s执行一次
        // 3、构建Trigger实例,每隔1s执行一次
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "triggerGroup1")
                .startNow()//立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)//每隔1s执行一次
                        .repeatForever()).build();//一直执行

        //4、执行
        scheduler.scheduleJob(jobDetail, trigger);
        System.out.println("--------scheduler start ! ------------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(1);
        scheduler.shutdown();
        System.out.println("--------scheduler shutdown ! ------------");

    }
}
