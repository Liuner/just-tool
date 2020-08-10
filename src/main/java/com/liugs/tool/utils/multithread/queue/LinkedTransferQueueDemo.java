package com.liugs.tool.utils.multithread.queue;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName LinkedTransferQueueDemo
 * @Description
 * @Author liugs
 * @Date 2020/7/14 16:02:21
 */
public class LinkedTransferQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedTransferQueue<PriorityBlockingQueueDemo.TempDemo> linkedQueue = new LinkedTransferQueue<>();

        //定义一个线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("transfer-demo-").build();

        ExecutorService pool = new ThreadPoolExecutor(3, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        // 生产者
        ProducerRunnable producer = new ProducerRunnable(linkedQueue);
        // 消费者
        ConsumerRunnable consumer1 = new ConsumerRunnable(linkedQueue);

        ConsumerRunnable consumer2 = new ConsumerRunnable(linkedQueue);

        // 开始运行
        pool.submit(producer);
        pool.submit(consumer1);
        pool.submit(consumer2);

        pool.shutdown();
    }
}
