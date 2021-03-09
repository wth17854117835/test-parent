package com.sitech.wth.test.cron;

import groovy.util.logging.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author: wangth_oup
 * @date: 2020-10-23 17:34
 * @description:
 **/
@EnableScheduling
@Component
@Slf4j
@ConditionalOnProperty(prefix = "test.schedul",name = "enabled",havingValue = "true")
public class AutoTask {
    private Logger logger = LogManager.getLogger(AutoTask.class);

    @Scheduled(fixedRate = 1000)
    private void process(){
        logger.error("autoTask ");
    }
}
