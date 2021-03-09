package com.sitech.wth.practice.designPattern.proxy;

/**
 * @author: wangth_oup
 * @date: 2020-12-01 10:57
 * @description: 静态代理
 * 代理对象（客户）与被代理对象（中介）必须实现同一个接口（买房），同时代理中持有一个被代理的引用。
 * 通过中介买房子，那么客户就是被代理对象，中介就是代理，行为就是买房，因此可以定义一个买房的接口。
 *
 * 代理模式和适配器模式最大的区别，代理模式是与原对象实现同一个接口，而适配器类则是匹配新接口，说白了，实现一个新的接口。
 **/

//代理类
public class ProxyBuyer implements Buy {
    //1.被代理类(客户)，代理类持有被代理的对象，即Buyer实现的接口Buy
    private Buy buy;
    //2.代理类持有被代理的对象，即Buyer
    private Buyer buyer;

    public ProxyBuyer(Buy buy) {
        this.buy = buy;
    }

    public ProxyBuyer(Buyer buyer) {
        this.buyer = buyer;
    }

    @Override
    public void buyHouse(long money) {
//        buy.buyHouse(money);
        buyer.buyHouse(money);
        System.out.println("中介费：" + money*0.02 + "元");
    }
}

interface Buy {
    void buyHouse(long money);
}

class Buyer implements Buy{
    @Override
    public void buyHouse(long money) {
        System.out.println("买房子花了："+money+"元");
    }
}

class Client {
    public static void main(String[] args) {
        System.out.println("----------------静态代理-----------------");
        //实例被代理的对象（买家）
        Buy buyer = new Buyer();
        //实例化代理类（中介），引入被代理对象
//        ProxyBuyer proxyBuyer = new ProxyBuyer(buyer);
        ProxyBuyer proxyBuyer = new ProxyBuyer(new Buyer());
        proxyBuyer.buyHouse(500000);
    }
}