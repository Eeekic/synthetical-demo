package com.huawei.service;

import com.huawei.manager.ConsumerManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class ScheduledTask {

    private static Logger log = Logger.getLogger(ManagerService.class);

    @Autowired
    ConsumerManager consumerManager;

    @Scheduled(fixedRate = 60000)
    public void print(){
        log.info("Topic:" + consumerManager.subscription());
        System.out.println(consumerManager.subscription());
    }
}
