package com.sitech.wth.service.impl;

import com.sitech.wth.service.inter.IHelloWorld;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: wangth_oup
 * @date: 2020-07-20 14:40
 * @description:
 **/
@Service("helloWorldSvc")
@RequestMapping("/test/api/accept")
public class HelloWorld implements IHelloWorld {

    private Logger logger = LogManager.getLogger(HelloWorld.class);

    @PostMapping("/helloWorld")
    @Override
    public String hello() {
        logger.info("我是info信息");
        logger.warn("我是warn信息");
        logger.error("我是error信息");
        logger.fatal("我是fatal信息");
        return "hello";
    }
}
