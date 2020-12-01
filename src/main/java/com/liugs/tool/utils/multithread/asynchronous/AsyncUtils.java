package com.liugs.tool.utils.multithread.asynchronous;


import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @ClassName AsyncUtil
 * @Description 异步通知
 * @Author liugs
 * @Date 2020/11/30 11:45:35
 */
public class AsyncUtils {

    private static ExecutorService executor;

    private static final String THREAD_NAME = "PAY-ASYNC";
    private static final int POOL_CORE_SIZE = 20;
    private static final int POOL_MAX_SIZE = 40;

    static {
        init();
    }

    /**
     * 描述 初始化线程池
     * @author liugs
     * @date 2020/11/30 11:55:10
     */
    synchronized private static void init() {
        if (null != executor) {
            executor.shutdownNow();
        }

        //定义一个线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(THREAD_NAME).build();

        executor = new ThreadPoolExecutor(
                POOL_CORE_SIZE,
                POOL_MAX_SIZE,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
    }

    /**
     * 描述 关闭线程池
     * @param isNow 是否立即关闭而不等待正在执行的线程
     * @author liugs
     * @date 2020/11/30 11:55:10
     */
    synchronized public static void shutdown(boolean isNow) {
        if (null != executor) {
            if (isNow) {
                executor.shutdownNow();
            } else {
                executor.shutdown();
            }
        }
    }

    /**
     * 描述 执行
     * @param runnable
     * @return void
     * @author liugs
     * @date 2020/12/1 9:49:53
     */
    public static void execAsync(Runnable runnable) {
        executor.execute(runnable);
    }
}
