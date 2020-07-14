package com.liugs.tool.utils.thread;

import cn.hutool.core.thread.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName SellPool
 * @Description
 * @Author liugs
 * @Date 2020/7/14 11:15:39
 */
public class SellPool {
    public static void main(String[] args) {

        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("Conductor").build();

        //Common Thread Pool
        ExecutorService pool = new ThreadPoolExecutor(3, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        SellingTickets selling = new SellingTickets();

        //从线程池中获取线程对象
        pool.submit(selling);

        //从线程池中获取线程对象
        pool.submit(selling);

        //从线程池中获取线程对象
        pool.submit(selling);

        pool.shutdown();
    }
}
