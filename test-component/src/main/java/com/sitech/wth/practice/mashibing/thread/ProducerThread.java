package com.sitech.wth.practice.mashibing.thread;

/**
 * @author: wangth_oup
 * @date: 2021-11-01 16:10
 * @description: 生产者
 **/
public class ProducerThread extends Thread{

    private Product product;

    public ProducerThread(Product product) {
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 9; i++) {
            if(i%2==0){
                product.setProduct("哈尔滨", "啤酒");
            } else {
                product.setProduct("费列罗", "巧克力");
            }
        }
    }
}
