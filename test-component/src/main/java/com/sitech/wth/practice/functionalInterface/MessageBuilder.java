package com.sitech.wth.practice.functionalInterface;

/**
 * 函数式接口：拼接消息
 **/
@FunctionalInterface
public interface MessageBuilder {
    public abstract String buildMessage();
}
