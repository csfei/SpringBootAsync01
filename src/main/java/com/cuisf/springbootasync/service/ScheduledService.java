package com.cuisf.springbootasync.service;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduledService {


    @Scheduled(cron = "0 * * * * 0-7")
    //cron 表达式~
    //秒  分  时  日  月  周几
    //   cron = "59 59 23 * * ?"  每天的23：59：59 执行
    public void  hello(){

        System.out.println("定时器 开始执行了。。。。。");
    }
}
