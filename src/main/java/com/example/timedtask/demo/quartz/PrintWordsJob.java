package com.example.timedtask.demo.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 *Job是Quartz中的一个接口，接口下只有execute方法，在这个方法中编写业务逻辑。
 */
public class PrintWordsJob implements Job {
    /**
     *  JobExecutionContext中包含了Quartz运行时的环境以及Job本身的详细数据信息。
     *当Schedule调度执行一个Job的时候，就会将JobExecutionContext传递给该Job的execute()中，Job就可以通过JobExecutionContext对象获取信息。
     * 具体信息看JobExecutionContext的api
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //TODO
        String printTime = new SimpleDateFormat("yy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("PrintWordsJob start at:" + printTime + ", prints: Hello Job-" + new Random().nextInt(100));
        System.out.println("hello world -----------------------------");
    }
}
