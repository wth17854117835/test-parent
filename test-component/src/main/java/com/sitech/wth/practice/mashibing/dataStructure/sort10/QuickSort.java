package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description: 快速排序：
 **/
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {7, 3, 8, 2, 1, 9, 5, 4, 6};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void quickSort(int[] arr, int leftBound, int rightBound) {
        if(rightBound <= leftBound) return;
        partition(arr, leftBound, rightBound);
    }

    private static void partition(int[] arr, int leftBound, int rightBound) {
        int pivot = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left < right) {
            while (arr[left] < arr[pivot]) left++;
        }


    }

}
