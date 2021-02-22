package com.liugs.tool.utils.multithread;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.*;

/**
 * @ClassName ExcuterConfig
 * @Description
 * @Author liugs
 * @Date 2021/2/2 10:52:55
 */
public class ExcuterConfig {

    /**
     * 最大可用的CPU核数
     */
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    /**
     * 线程最大的空闲存活时间，单位为秒
     */
    public static final int KEEP_ALIVE_TIME = 10;
    /**
     * 缓冲队列数
     */
    private static final int QUEUE_CAPACITY = 1024;
    /**
     * 线程池名前缀
     */
    private static final String THREAD_NAME_PREFIX = "PAY-ASYNC-";

    private final ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix(THREAD_NAME_PREFIX +"%d").build();

    @Bean("payAsyncExecutor")
    public Executor payAsyncExecutor() {
        ExecutorService pool = new ThreadPoolExecutor(
                PROCESSORS * 2,
                PROCESSORS * 4,
                KEEP_ALIVE_TIME * 1000,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(QUEUE_CAPACITY),
                threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
        return pool;
    }
}
