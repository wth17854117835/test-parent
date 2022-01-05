package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description:
 *
 * 冒泡排序：
 * 一次比较两个元素，如果它们的顺序错误就把它们交换过来
 **/
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {5,3,6,8,1,7,9,4,2};
        bubbleSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length -1; i++) {
//        for (int i = 0; i < 4; i++) { //找出top4
            for (int j = 0; j < arr.length -1 -i; j++) {
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
