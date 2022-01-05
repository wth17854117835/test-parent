package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description: 希尔排序：基于插入排序
 * 它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 **/
public class ShellSort {

    public static void main(String[] args) {
        int[] arr = {5, 3, 6, 8, 1, 7, 9, 4, 2};
//        insertionSort(arr);
        shellSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void shellSort(int[] arr) {
        for (int gap = arr.length >> 1; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = temp;
                    }
                }
            }
        }
    }

}
