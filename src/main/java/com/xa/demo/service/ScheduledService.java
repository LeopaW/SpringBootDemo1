package com.xa.demo.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    /**
     * second minute hour day of month month day of week
     * 0 * * * * MON-FAI
     * 后面写的星期可以写英文的也可以学数字,    0和7都是代表星期天
     */
    //@Scheduled(cron = "0 * * * * MON-SAT")  //周一到周六每分钟秒为0时打印
    //@Scheduled(cron = "0,1,2,3,4 * * * * MON-SAT")  //周一到周六每分钟秒为0,1,2,3,4时打印
    //@Scheduled(cron = "0-4 * * * * MON-SAT")
    //@Scheduled(cron = "0/4 * * * * MON-SAT")  //每四秒执行一次
    public void hello(){
        System.out.println("hello ....");
    }
}
