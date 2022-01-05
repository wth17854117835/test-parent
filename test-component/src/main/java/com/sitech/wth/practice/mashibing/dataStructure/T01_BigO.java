package com.sitech.wth.practice.mashibing.dataStructure;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 10:50
 * @description:
 *
 * 取数组下标为固定值的数
 * 算法与数组长度无关，跟取的第几个位置也无关，这种时间复杂度我们称为 O(1)
 * 求平均数所花费的时间，明显跟数组长度有关，这个关系和数组长度成正比，我们称为 O(n)
 * 这里的n是问题的规模，或者说是数据的规模
 **/
public class T01_BigO {

    public static void main(String[] args) {
        byte aa = 12;// -128 ~ 127
        byte ab = (byte) 1200;//0100 1011 0000  --> 1011 0000  --> 取反 1100 1111 --> +1 --> 补码1101 0000 --> -80
        byte ac = (byte) 270;//0001 0000 1110 --> 0000 1110  -->正数的原码=反码=补码 --> 15
        System.out.println(aa);//12
        System.out.println(ab);//-80
        System.out.println(ac);//14
        //计算机存储的补码先转换成反码，后转换成原码，再转换成十进制呈现的。因为用原码和补码计算会有问题
        //原码、反码与补码的关系：
        //正数：  原码  =  反码  = 补码
        //负数：  原码取反  = 反码（符号位不变）；  反码  +  1  = 补码（符号位上的进位舍弃）
        //所以(byte)1200 --> -80 的原因是：byte只有1个字节8位，舍弃后原码为 1011 0000 ---取反---> 反码为 1100 1111 --+1--> 补码为 1101 0000 ==> 转换成十进制为 -80

        int[] a = new int[10_000_000];
        for (int i = 0; i < a.length; i++) {
            a[i] = i;
        }

        //算法开始时间
        long start = System.currentTimeMillis();

        for (long i = 0; i < 100000L; i++) {
            a[100000] = 8;
//            avg(a);
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static int avg(int[] arr){
        long sum = 0;
        for (int i = 0; i<arr.length;i++){
            sum += arr[i];
        }
        return (int) (sum/arr.length);
    }
}
