package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description:
 *
 * 插入排序：
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 **/
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {5,3,6,8,1,7,9,4,2};
//        insertionSort(arr);
        insertionSort2(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void insertionSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int preIndex = i-1;
            int currentValue = arr[i];
            while (preIndex >= 0 && currentValue < arr[preIndex]) {
                arr[preIndex+1] = arr[preIndex];
                preIndex--;
            }
            arr[preIndex+1] = currentValue;
        }
    }

    private static void insertionSort2(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
    }

}
