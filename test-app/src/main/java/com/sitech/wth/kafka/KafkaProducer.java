package com.sitech.wth.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wangth
 * @create: 2022-03-02 16:31
 * @description： kafka消息生产者
 */
@RestController
@RequestMapping(value = "/kafka")
public class KafkaProducer {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    // 发送消息
    @GetMapping("/normal/{message}")
    public void sendMessage1(@PathVariable("message") String normalMessage) {
        kafkaTemplate.send("topic1", normalMessage);
    }

    // 带回调的生产者
    @GetMapping("/callbackOne/{message}")
    public void sendMessage2(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(success -> {
            // 消息发送到的topic
            String topic = success.getRecordMetadata().topic();
            // 消息发送到的分区
            int partition = success.getRecordMetadata().partition();
            // 消息在分区内的offset
            long offset = success.getRecordMetadata().offset();
            System.out.println("发送消息成功:" + topic + "-" + partition + "-" + offset);
        }, failure -> {
            System.out.println("发送消息失败:" + failure.getMessage());
        });
    }

    @GetMapping("/callbackTwo/{message}")
    public void sendMessage3(@PathVariable("message") String callbackMessage) {
        kafkaTemplate.send("topic1", callbackMessage).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("发送消息失败：" + ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                System.out.println("发送消息成功：" + result.getRecordMetadata().topic() + "-"
                        + result.getRecordMetadata().partition() + "-" + result.getRecordMetadata().offset());
            }
        });
    }

    //发消息时需要创建事务，使用 executeInTransaction()方法声明事务
    @GetMapping("/transaction")
    public void sendMessage7() {
        // 声明事务：后面报错消息不会发出去
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("topic1", "test executeInTransaction");
            throw new RuntimeException("fail");
        });
        // 不声明事务：后面报错但前面消息已经发送成功了
        kafkaTemplate.send("topic1", "test executeInTransaction");
        throw new RuntimeException("fail");
    }
}
