package com.liugs.tool.utils.multithread.asynchronous;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadPoolManager
 * @Description
 * @Author liugs
 * @Date 2020/12/1 14:03:06
 */
public class ThreadPoolManager {

    private final String PREFIX = "POOL-MANAGER-";

    /**核心线程数为 CPU数＊2*/
    private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    /**线程队列最大线程数*/
    private static final int MAXIMUM_POOL_SIZE = 64;

    /**保持存活时间 1秒*/
    private static final int KEEP_ALIVE_TIME = 1;

    private final BlockingQueue<Runnable> mWorkQueue = new LinkedBlockingQueue<>(128);

    private static volatile ThreadPoolManager instance;

    private ThreadPoolManager() {
    }

    public static synchronized ThreadPoolManager getInstance() {
        if (null == instance) {
            instance = new ThreadPoolManager();
        }
        return instance;
    }


    private final ThreadFactory DEFAULT_THREAD_FACTORY = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, PREFIX + "-" + mCount.getAndIncrement());
            thread.setPriority(Thread.NORM_PRIORITY);
            return thread;
        }
    };
    
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAXIMUM_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            mWorkQueue,
            DEFAULT_THREAD_FACTORY,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    /**
     * 描述
     * @param runnable
     * @return void
     * @author liugs
     * @date 2020/12/2 14:36:09
     */
    public void addTask(Runnable runnable) {
        executor.execute(runnable);
    }

    public void shutdown() {
        shutdownNow(false);
    }

    public void shutdownNow(Boolean isNow) {
        if (isNow) {
            executor.shutdownNow();
        }
        executor.shutdown();
    }

}
