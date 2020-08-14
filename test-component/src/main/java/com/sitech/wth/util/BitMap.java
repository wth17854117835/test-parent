package com.sitech.wth.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 9:39
 * @description: bitmap
 * |=：两个二进制对应位都为0时，结果等于0，否则结果等于1；
 *  0000 0101
 *  0000 0011
 *  ---------
 *  0000 0111
 * &=：两个二进制的对应位都为1时，结果为1，否则结果等于0；
 *  0000 0101
 *  0000 0011
 *  ---------
 *  0000 0001
 * ^=：两个二进制的对应位相同，结果为0，否则结果为1。
 *  0000 0101
 *  0000 0011
 *  ---------
 *  0000 0110
 **/
public class BitMap {
    private static final int N = 10000000;
    private int[] a = new int[N/32 + 1];

    /**
     * 设置所在的bit位为1
     * @param n
     */
    public void addValue(int n){
        //row = n / 32 求十进制数在数组a中的下标
        int row = n >> 5;
        //保留n的后五位，相当于 n % 32 求十进制数在数组a[i]中的下标
        // 0x1f 0x代表16进制 ==> 二进制为31 0001 1111
        int i = n & 0x1F;
        a[row] |= 1 << (n & 0x1F);
    }

    // 判断所在的bit为是否为1
    public boolean exits(int n){
        int row = n >> 5;
        return (a[row] & ( 1 << (n & 0x1F))) != 1;
    }

    public void display(int row){
        System.out.println("BitMap位图展示");
        for(int i=0;i<row;i++){
            List<Integer> list = new ArrayList<Integer>();
            int temp = a[i];
            for(int j=0;j<32;j++){
                list.add(temp & 1);
                temp >>= 1;
            }
            System.out.println("a["+i+"]" + list);
        }
    }

    public static void main(String[] args){
        int num[] = {1,5,30,32,64,56,159,120,21,17,35,45};

        BitMap map = new BitMap();
        for(int i=0;i<num.length;i++){
            map.addValue(num[i]);
        }

        int temp = 120;
        if(map.exits(temp)){
            System.out.println("temp:" + temp + "has already exists");
        }

        int max = 0;
        for (int i = 0; i < num.length; i++) {
            if(num[i] > max){
                max = num[i];
            }
        }
        int row = (max >> 5) + 1;
        map.display(row);
    }
}
