package com.liugs.tool.utils.multithread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.liugs.tool.constants.Console;

import java.util.concurrent.*;

/**
 * @ClassName UTest
 * @Description
 * @Author liugs
 * @Date 2020/7/13 15:13:16
 */
public class UTest {
    public static void main(String[] args) {
        threadFactoryDemo();

//        ThreadPoolExecutorDemo();
    }

    private static void threadFactoryDemo() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("demo-pool-").build();
        /**
         * 1.corePoolSize：向线程池中添加任务时，如果线程池中已创建的线程数小于corePoolSize，
         *   即使有空线程，也会创建新的线程来执行该任务，直到线程数大于或等于corePoolSize。
         * 2.在线程数大于corePoolSize时，如果线程空闲的时间超过了设置的keepAliveTime，
         *   该线程会被回收，线程回收时没有什么所谓的核心和非核心之分，谁空闲回收谁，知道线程数等于corePollSize
         * 3.
         */

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(2, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                namedThreadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        pool.execute(()->Console.show(Thread.currentThread().getName()));

        //创建Runnable线程任务对象
        UTaskRunnable task = new UTaskRunnable();

        Console.show("----------------------");

        //从线程池中获取线程对象
        pool.submit(task);

        //再获取一个线程对象
        pool.submit(task);

        //gracefully shutdown
        pool.shutdown();
    }


    private static void threadPoolExecutorDemo() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,
                1, TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(1024)
        );
        //一些不常用的设置
        //allowCoreThreadTimeOut 将包括“核心线程”在内的，没有任务分配的任何线程，在等待keepAliveTime时间后全部进行回收
        threadPoolExecutor.allowCoreThreadTimeOut(true);

        //prestartAllCoreThreads 可以在线程池创建，但还没有接收到任何任务的情况下，先行创建符合corePoolSize参数值的线程数
        threadPoolExecutor.prestartAllCoreThreads();

        threadPoolExecutor.execute(()->Console.show(Thread.currentThread().getName() + " execute"));

        threadPoolExecutor.shutdown();
    }
}
