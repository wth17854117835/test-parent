package com.sitech.wth.practice.mashibing.thread;

/**
 * @author: wangth_oup
 * @date: 2021-11-01 16:10
 * @description: 消费者
 **/
public class ConsumerThread extends Thread{

    private Product product;

    public ConsumerThread(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            product.getProduct();
        }
    }
}
