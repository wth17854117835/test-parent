package com.sitech.wth.practice.mashibing.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: wangth_oup
 * @date: 2021-11-01 16:09
 * @description: 商品（生产者--消费者问题）
 **/
public class Product {
    private String brand;
    private String name;

    boolean flag = false;

    Lock lock = new ReentrantLock();
    Condition produceCondition = lock.newCondition();
    Condition consumeCondition = lock.newCondition();


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(String brand,String name) {
        //获取锁
        lock.lock();
        try {
            if(flag){
                try {
                    //进入生产者等待队列
                    produceCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.setBrand(brand);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setName(name);
            System.out.println("生产者生产了："+this.getBrand()+"----"+this.getName());
            flag = true;
            consumeCondition.signal();//唤醒消费者队列中的一个消费者
        } finally {
            //释放锁
            lock.unlock();
        }
    }

    public void getProduct() {
        lock.lock();
        try {
            if(!flag){
                try {
                    consumeCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("消费者消费了："+this.getBrand()+"----"+this.getName());
            flag = false;
            produceCondition.signal();
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Product product = new Product();
        ProducerThread producerThread = new ProducerThread(product);
        ConsumerThread consumerThread = new ConsumerThread(product);
        producerThread.start();
        consumerThread.start();
    }

}
