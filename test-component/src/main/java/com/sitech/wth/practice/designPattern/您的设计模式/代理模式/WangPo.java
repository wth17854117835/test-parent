package com.sitech.wth.practice.designPattern.您的设计模式.代理模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:43
 * 代理类：王婆 实现了 KindWomen接口，并作为类的属性
 */
public class WangPo implements KindWomen{

    private KindWomen kindWomen;

    public WangPo(){ //默认的话，是潘金莲的代理
        this.kindWomen = new PanJinLian();
    }

    //她可以是KindWomen的任何一个女人的代理，只要你是这一类型
    public WangPo(KindWomen kindWomen){
        this.kindWomen = kindWomen;
    }

    public void happyWithMan() {
        this.kindWomen.happyWithMan(); //自己老了，干不了，可以让年轻的代替
    }
    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan(); //王婆这么大年龄了，谁看她抛媚眼？！
    }
}
