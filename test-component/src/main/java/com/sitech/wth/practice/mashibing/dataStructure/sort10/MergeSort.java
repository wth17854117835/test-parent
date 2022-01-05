package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description: 归并排序：
 * 把长度为n的输入序列分成两个长度为n/2的子序列；
 * 对这两个子序列分别采用归并排序；
 * 将两个排序好的子序列合并成一个最终的排序序列。
 **/
public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 7, 8, 3, 6, 9};
//        merge(arr);
        merge(arr, 0, 4, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void merge(int[] arr, int leftPtr, int rightPtr, int rightBound) {
//        int mid = arr.length / 2;
//        int[] temp = new int[arr.length];

//        int i = 0;
//        int j = mid + 1;
//        int k = 0;
        int mid = arr.length / 2;
        int[] temp = new int[rightBound - rightPtr + 1];

        int i = leftPtr;
        int j = rightPtr;
        int k = 0;

//        while (i <= mid && j <arr.length) {
        while (i <= mid && j <= rightBound) {
//            temp[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
            if(arr[i] <= arr[j]){
                temp[k] = arr[i];
                i++;
                k++;
            } else {
                temp[k] = arr[j];
                j++;
                k++;
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j < arr.length) temp[k++] = arr[j++];

        for (int m = 0; m < temp.length; m++) {
            System.out.println(temp[m] + " ");
        }
    }

}
