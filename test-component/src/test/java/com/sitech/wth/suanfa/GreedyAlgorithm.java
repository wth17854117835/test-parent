package com.sitech.wth.suanfa;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wangth_oup
 * @date: 2020-08-13 14:42
 * @description: 贪心算法
 **/
public class GreedyAlgorithm {

    /**
     * 【活动安排问题】
     * 设有n个活动的集合e={1，2，…，n}，其中每个活动都要求使用同一资源，如演讲会场等，而在同一时间内只有一个活动能使用这一资源。
     * 每个活动i都有一个要求使用该资源的起始时间si和一个结束时间fi,且si< fi。如果选择了活动i，则它在半开时间区间[si，fi]内占用资源。
     * 若区间[si，fi]与区间[sj，fj]不相交，则称活动i与活动j是相容的。也就是说，当si≥fi或sj≥fj时，活动i与活动j相容。
     * 活动安排问题就是要在所给的活动集合中选出最大的相容活动子集合。
     * 各活动的起始时间和结束时间存储于数组start和end中且按结束时间的非减序：．f1≤f2≤…≤fn排列。
     */
    @Test
    public void testArrangeActivity() {
        int[] start = {1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12};
        int[] end = {4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
        List<Integer> results = arrangeActivity(start, end);
        for (int i = 0; i < results.size(); i++) {
            int index = results.get(i);
            System.out.println("开始时间:" + start[index] + ",结束时间:" + end[index]);
        }
    }

    /**
     * 活动安排
     *
     * @param s 开始时间
     * @param e 结束时间
     */
    public List<Integer> arrangeActivity(int[] s, int[] e) {
        int total = s.length;
        int endFlag = e[0];
        List<Integer> results = new ArrayList<>();
        results.add(0);
        for (int i = 0; i < total; i++) {
            if (s[i] > endFlag) {
                results.add(i);
                endFlag = e[i];
            }
        }
        return results;
    }

    /**
     * 【找零钱问题】
     * 假如老板要找给我99分钱，他有上面的面值分别为25，10，5，1的硬币数，
     * 为了找给我最少的硬币数，那么他是不是该这样找呢，先看看该找多少个25分的，诶99／25＝3，好像是3个，
     * 要是4个的话，我们还得再给老板一个1分的，我不干，
     * 那么老板只能给我3个25分的拉，由于还少给我24，所以还得给我2个10分的和4个1分。
     */
    @Test
    public void testGiveMoney() {
        //找零钱
        int[] m = {25, 10, 5, 1};
        int target = 99;
        int[] results = giveMoney(m, target);
        System.out.println(target + "的找钱方案:");
        for (int i = 0; i < results.length; i++) {
            System.out.println(results[i] + "枚" + m[i] + "面值");
        }
    }

    public int[] giveMoney(int[] m, int target) {
        int k = m.length;
        int[] results = new int[k];
        for (int i = 0; i < k; i++) {
            results[i] = target / m[i];
            target = target % m[i];
        }
        return results;
    }

    /**
     * 【均分纸牌】
     * 有N堆纸牌，编号分别为1，2，…，n。每堆上有若干张,但纸牌总数必为n的倍数。可以在任一堆上取若干张纸牌,然后移动。
     * 移牌的规则为：在编号为1上取的纸牌，只能移到编号为2的堆上；在编号为n的堆上取的纸牌，只能移到编号为n-1的堆上；
     * 其他堆上取的纸牌，可以移到相邻左边或右边的堆上。
     * 现在要求找出一种移动方法，用最少的移动次数使每堆上纸牌数都一样多。
     * 例如：n=4，4堆纸牌分别为：(A) 9 (B) 8 (C) 17 (D) 6 移动三次可以达到目的：从C取4张牌放到D 再从C区3张放到B然后从B去1张放到A。
     * <p>
     * 算法分析：设a[i]为第I堆纸牌的张数（0<=I<=n），v为均分后每堆纸牌的张数，s为最小移动次数。
     * 我们用贪心算法，按照从左到右的顺序移动纸牌。如第I堆的纸牌数不等于平均值，则移动一次（即s加1），分两种情况移动：
     * 1．若a[i]>v，则将a[i]-v张从第I堆移动到第I+1堆；
     * 2．若a[i]< v，则将v-a[i]张从第I+1堆移动到第I堆。
     * 为了设计的方便，我们把这两种情况统一看作是将a[i]-v从第I堆移动到第I+1堆，移动后有a[i]=v; a[I+1]=a[I+1]+a[i]-v.
     * 在从第I+1堆取出纸牌补充第I堆的过程中可能回出现第I+1堆的纸牌小于零的情况。
     * 如n=3，三堆指派数为1 2 27 ，这时v=10，为了使第一堆为10，要从第二堆移9张到第一堆，而第二堆只有2张可以移，
     * 这是不是意味着刚才使用贪心法是错误的呢？
     * 我们继续按规则分析移牌过程，从第二堆移出9张到第一堆后，第一堆有10张，第二堆剩下-7张，
     * 在从第三堆移动17张到第二堆，刚好三堆纸牌都是10，最后结果是对的，
     * 我们在移动过程中，只是改变了移动的顺序，而移动次数不便，因此此题使用贪心法可行的。
     */
    @Test
    public void testMoveCard() {
        //总共4堆
        int heap = 4;
//        int[] cards = {9, 8, 17, 6};
        int[] cards = {10, 10, 20, 0};
        int count = moveCards(cards, heap);
        System.out.println("移动次数:" + count);
        for (int i = 0; i < cards.length; i++) {
            System.out.println("第" + (i + 1) + "堆牌数:" + cards[i]);
        }
    }

    /**
     * 均分纸牌
     *
     * @param cards 卡牌
     * @param heap  堆数
     */
    public int moveCards(int[] cards, int heap) {
        //总牌数
        int sum = 0;
        for (int i = 0; i < cards.length; i++) {
            sum += cards[i];
        }
        //每堆平均牌数
        int avg = sum / heap;
        //移动次数
        int count = 0;
        for (int i = 0; i < cards.length; i++) {
            if (cards[i] != avg) {
                int moveCards = cards[i] - avg;
                cards[i] -= moveCards;
                cards[i + 1] += moveCards;
                count++;
            }
        }
        return count;
    }

    /**
     * 【最大整数】
     * 设有n个正整数，将它们连接成一排，组成一个最大的多位整数。
     * 例如：n=3时，3个整数13，312，343，连成的最大整数为34331213。
     * 又如：n=4时，4个整数7，13，4，246，连成的最大整数为7424613。
     * 输入：n
     * 输出：连成的多位数
     * 算法分析：此题很容易想到使用贪心法，在考试时有很多同学把整数按从大到小的顺序连接起来，
     * 测试题目的例子也都符合，但最后测试的结果却不全对。
     * 按这种标准，我们很容易找到反例：12，121应该组成12121而非12112，那么是不是相互包含的时候就从小到大呢？
     * 也不一定，如12，123就是12312而非12123，这种情况就有很多种了。
     * 是不是此题不能用贪心法呢？
     * 其实此题可以用贪心法来求解，只是刚才的标准不对，
     * 正确的标准是：先把整数转换成字符串，然后在比较a+b和b+a，如果a+b>=b+a，就把a排在b的前面，反之则把a排在b的后面。
     */
    @Test
    public void testMaxNum() {
        //有n个正整数，将它们连接成一排，组成一个最大的多位整数
        //12112错误
        //12121正解
//        int[] nums = {12, 121};
//        int[] nums = {12, 123};
        int[] nums = {13,7,4,246};
        String result = maxNum(nums);
        System.out.println("组成最大整数:" + result);
    }

    /**
     * 根据给定的整数组成最大的多位数
     * @param nums
     */
    public String maxNum(int[] nums) {
        String result = "";
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                String num1 = nums[i] + "";
                String num2 = nums[j] + "";
                if ((num1 + num2).compareTo(num2 + num1) < 0) {
                    int temp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = temp;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }
        return result;
    }
}
