package com.sitech.wth.practice.mashibing.dataStructure.sort10;

/**
 * @author: wangth_oup
 * @date: 2021-08-05 14:23
 * @description:
 *
 * 选择排序：
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，
 * 然后再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 **/
public class SelectionSort {

    public static void main(String[] args) {
        int[] arr = {5,3,6,8,1,7,9,4,2};

        //外循环：每循环一次，找出最大（小）的元素
        for (int i = 0; i < arr.length - 1; i++) {
            int minPos = i;

            //内循环：依次对每个元素进行比较，找出最大（小）的元素下标
            for (int j = i+1; j < arr.length; j++) {
//                if (arr[j] < arr[minPos]) {
//                    minPos = j;
//                }
                minPos = arr[j] < arr[minPos] ? j : minPos;
            }
            //交换
            swap(arr, i, minPos);
//            int temp = arr[i];
//            arr[i] = arr[minPos];
//            arr[minPos] = temp;
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
