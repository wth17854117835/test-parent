package com.sitech.wth.practice.designPattern.您的设计模式.建造者模式;

import java.util.ArrayList;

/**
 * @author: wangth
 * @create: 2022-02-14 15:11
 */
public abstract class CarBuilder {

    //建造一个模型，你要给我一个顺序要，就是组装顺序
    public abstract void setSequence(ArrayList<String> sequence);

    //设置完毕顺序后，就可以直接拿到这个车辆模型
    public abstract CarModel getCarModel();
}
