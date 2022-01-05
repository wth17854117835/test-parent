package com.sitech.wth.practice.mashibing.juc;

import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: wangth_oup
 * @date: 2021-12-10 15:38
 * @description: 路人甲--手写一个并行处理任务的工具类
 **/
public class T00_TaskDisposeUtils {

    public static final int POOL_SIZE;

    static {
        POOL_SIZE = Integer.max(Runtime.getRuntime().availableProcessors(), 5);
    }

    //并行处理
    public static <T> void dispose(List<T> taskList, Consumer<T> consumer) {
        dispose(true,POOL_SIZE,taskList,consumer);
    }

    /**
     * 并行处理并等待结束
     * @param isMoreThread 是否启用多线程
     * @param poolSize 线程池大小
     * @param taskList 任务列表
     * @param consumer 消费者
     * @param <T>
     */
    public static <T> void dispose(boolean isMoreThread, int poolSize, List<T> taskList, Consumer<T> consumer) {
        if(CollectionUtils.isEmpty(taskList)) {
            return;
        }
        if (isMoreThread && poolSize > 1) {
            poolSize = Math.min(poolSize, taskList.size());
            ExecutorService executorService = null;
            try {
                executorService = Executors.newFixedThreadPool(poolSize);
                CountDownLatch countDownLatch = new CountDownLatch(taskList.size());
//            taskList.forEach(task -> {
                for (T task : taskList) {
                    executorService.execute(() -> {
                        try {
                            consumer.accept(task);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            countDownLatch.countDown();
                        }
                    });
                }
                countDownLatch.await();
//            });
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if(executorService != null) {
                    executorService.shutdown();
                }
            }
        } else {
            taskList.forEach(a -> consumer.accept(a));
        }
    }

    public static void main(String[] args) {
        //生成1-10的10个数字，放在list中，相当于10个任务
        List<Integer> list = Stream.iterate(1, a -> a + 1).limit(10).collect(Collectors.toList());
        //启动多线程
        T00_TaskDisposeUtils.dispose(list, item -> {
            try {
                long start = System.currentTimeMillis();
                TimeUnit.SECONDS.sleep(item);
                long end = System.currentTimeMillis();
                System.out.println(System.currentTimeMillis() + "任务" + item + "执行完毕，耗时：" + (end - start));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(list + "中的任务都处理完毕！");
    }
}
