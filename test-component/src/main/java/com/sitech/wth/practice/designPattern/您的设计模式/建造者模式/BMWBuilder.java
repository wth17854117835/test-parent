package com.sitech.wth.practice.designPattern.您的设计模式.建造者模式;

import java.util.ArrayList;

/**
 * @author: wangth
 * @create: 2022-02-14 15:13
 */
public class BMWBuilder extends CarBuilder{

    private BMWModel bmw = new BMWModel();

    @Override
    public void setSequence(ArrayList<String> sequence) {
        this.bmw.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}
