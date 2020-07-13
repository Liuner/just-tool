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

        //创建线程池对象  参数5，代表有5个线程的线程池
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNamePrefix("demo-pool-").build();
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
}
