package com.sitech.wth.suanfa;

/**
 * @author: wangth_oup
 * @date: 2020-08-14 15:45
 * @description: 排序 https://www.cnblogs.com/onepixel/p/7674659.html
 **/
public class Sort {

    public static void main(String[] args) {
        int[] arr = {15,6,3,16,7};
//        bubbleSort(arr);
//        selectionSort(arr);
        insertionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    /**
     * 冒泡排序:
     * 1.比较相邻的元素。如果第一个比第二个大，就交换它们两个；
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对，这样在最后的元素应该会是最大的数；
     * 3.针对所有的元素重复以上的步骤，除了最后一个；
     * 重复步骤1~3，直到排序完成。
     */
    private static void bubbleSort(int[] array){
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length-1-i; j++) {
                if(array[j] > array[j+1]) {
                    // 元素交换
                    int temp = array[j+1];
                    array[j+1] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    /**
     * 选择排序:
     * [15,6,3,16,7]
     */
    private static void selectionSort(int[] array){
        int len = array.length;
        int min;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if(array[j] < array[min]){
                    min = j;
                }
            }
            temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
    }

    /**
     * 插入排序
     *
     */
    private static void insertionSort(int[] array){
        int len = array.length;
        int preIndex;
        int current;
        for (int i = 1; i < len; i++) {
            preIndex = i-1;
            current = array[i];
            while (preIndex >= 0 && array[preIndex] > current){
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
    }


}
