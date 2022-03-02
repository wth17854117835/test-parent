package com.sitech.wth.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @author: wangth
 * @create: 2022-03-02 16:32
 * @description：
 */
@Component
public class KafkaConsumer {
    // 消费监听
    @KafkaListener(topics = {"topic1"})
    public void onMessage1(ConsumerRecord<?, ?> record) {
        // 消费的哪个topic、partition的消息,打印出消息内容
        System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "-" + record.value());
    }

    // id：消费者ID；
    // groupId：消费组ID；
    // topics：监听的topic，可监听多个；
    // topicPartitions：可配置更加详细的监听信息，可指定topic、parition、offset监听。
    // topics和topicPartitions不能同时使用
    @KafkaListener(id = "consumer1", groupId = "felix-group", topicPartitions = {
            @TopicPartition(topic = "topic1", partitions = {"0"}),
            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
    })
    public void onMessage2(ConsumerRecord<?, ?> record) {
        System.out.println("topic:" + record.topic() + "|partition:" + record.partition() + "|offset:" + record.offset() + "|value:" + record.value());
    }
}
