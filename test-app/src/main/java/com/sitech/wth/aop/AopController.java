package com.sitech.wth.aop;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

/**
 * @author: wangth_oup
 * @date: 2020-10-21 9:16
 * @description:
 **/
@RestController
@RequestMapping(value = "/aop")
public class AopController {
    @GetMapping(value = "/getTest")
    @ControllerMethodLog
    public String aopTest() {
        return "hello";
    }

    @PostMapping(value = "/postTest")
    public String aopTest2(@RequestParam("id") String id) {
        return "hello2 postTest";
    }
}
