package com.sitech.wth.aop;

import com.alibaba.fastjson.JSON;
import groovy.util.logging.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author: wangth_oup
 * @date: 2020-10-19 19:31
 * @description:
 **/
//@Slf4j
@Component
@Aspect
public class ControllerMethodLogAspect {
    private Logger log = LogManager.getLogger(ControllerMethodLogAspect.class);

    @Pointcut("@annotation(com.sitech.wth.aop.ControllerMethodLog)")
    public void pointCut() {
    }

    /**
     * 在切点运行前执行该方法
     */
    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 获取签名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取即将执行的方法
        Method method = signature.getMethod();
        ControllerMethodLog annotation = method.getAnnotation(ControllerMethodLog.class);
        if (Objects.isNull(annotation)) {
            return;
        }
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        log.info("start {}：入参：{}", methodName, JSON.toJSONString(joinPoint.getArgs()));
        System.out.println("start :>>>>>>>>>>"+methodName+",入参：>>>>>>>>>>" + JSON.toJSONString(joinPoint.getArgs()));
    }

    /**
     * 在切点运行后,无异常时执行该方法
     */
    @AfterReturning(value = "pointCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ControllerMethodLog annotation = method.getAnnotation(ControllerMethodLog.class);
        if (Objects.isNull(annotation)) {
            return;
        }
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        log.info("end {}：响应：{}", methodName, JSON.toJSONString(result));
        System.out.println("end :>>>>>>>>>>"+methodName+",响应：>>>>>>>>>>" + JSON.toJSONString(result));

    }

}
