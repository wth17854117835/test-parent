package com.sitech.wth.practice.designPattern.您的设计模式.代理模式;

/**
 * @author: wangth
 * @create: 2022-02-13 22:44
 * 什么是代理模式呢？我很忙，忙的没空理你，那你要找我呢就先找我的代理人吧，那代理人总要知道
 * 被代理人能做哪些事情不能做哪些事情吧，那就是两个人具备同一个接口，代理人虽然不能干活，但是被
 * 代理的人能干活呀。
 * 代理模式主要使用了 Java 的多态，干活的是被代理类，代理类主要是接活，
 * 你让我干活，好，我交给幕后的类去干，你满意就成。
 */
public class XiMenQing {

    public static void main(String[] args) {
        //把王婆叫出来
//        WangPo wangPo = new WangPo(); //默认代理的是潘金莲
        WangPo wangPo = new WangPo(new PanJinLian());
        //西门庆勾贾氏：
//        JiaShi jiaShi = new JiaShi();
//        WangPo wangPo = new WangPo(jiaShi); //让王婆作为贾氏的代理人
        //然后西门庆就说，我要和潘金莲happy，然后王婆就安排了西门庆丢筷子的那出戏:
        wangPo.makeEyesWithMan(); //看到没，虽然表面上时王婆在做，实际上爽的是潘金莲
        wangPo.happyWithMan();
    }
}
