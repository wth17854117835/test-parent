package com.sitech.wth.practice.designPattern.您的设计模式.建造者模式;

import java.util.ArrayList;

/**
 * @author: wangth
 * @create: 2022-02-14 15:12
 */
public class BenzBuilder extends CarBuilder{

    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}
