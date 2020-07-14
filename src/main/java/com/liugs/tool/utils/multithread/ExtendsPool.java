package com.liugs.tool.utils.multithread;

import com.liugs.tool.constants.Console;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 一个扩展的线程池，用于测试ThreadPoolExecutor中的扩展方法
 * @author yinwenjie
 *
 */
public class ExtendsPool extends ThreadPoolExecutor {


    public ExtendsPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }


    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        TestRunnable testRunnable = (TestRunnable)r;
        Console.show("beforeExecute(Thread t, Runnable r) : " + testRunnable.getIndex());
    }


    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        TestRunnable testRunnable = (TestRunnable)r;
        Console.show("afterExecute(Runnable r, Throwable t) : " + testRunnable.getIndex());
    }


    @Override
    protected void terminated() {
        Console.show("terminated() ！！");
    }

    public static void main(String[] args) throws Throwable {
        // 这个做法，是故意让后续index == 5 - 9的线程进入等待队列。以便观察执行现象
        ExtendsPool extendsPool = new ExtendsPool(5, 5,
                10000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(5));
        for(int index = 0 ; index < 10 ; index ++) {
            // 一定要使用execute，不要使用submit。后文讲解原因
            extendsPool.execute(new TestRunnable(index));
        }

        /**
         *  execute方法：所有实现了Runnable接口的任务都可以使用execute方法进行提交。而实现了Runnable接口的任务，
         * 并没有提供任何“标准”的方式为我们返回任务的执行结果（这是我们还没有讲到的知识点）。线程在线程池中运行结束了，
         * 就结束了。所以，使用execute方法提交的任务，程序员并不能在任务执行完成后，获得一个“标准”的执行结果。
         *
         *  submit方法：submit方法提交的任务是实现了Callable接口的任务（这是我们还没有讲到的知识点）。
         * Callable接口的特性是，在其运行完成后，会返回一个“标准”的执行结果。
         */
        /**
         * 所以为什么在示例代码中，会说明只能使用extendsPool.execute提交任务，
         * 而不要使用extendsPool.submit提交任务。因为如果使用extendsPool.submit提交任务，
         * 那么hook method：beforeExecute和afterExecute，虽然可以拿到一个Runnable对象，
         * 但是这个Runnable对象却不是创建的Runnable任务本身。而是一个FutureTask对象，
         * 里面封装了一个RunnableAdapter对象，在RunnableAdapter对象里面，才是Runnable任务本身
         */

        // 发出停止指令。注意停止指令本身不会等待，要使用awaitTermination进行等待。
        // 注意，按照我们上文讲过的线程池的工作原理，线程池在收到shutdown终止指令后
        // 就不会再接受提交过来的任务了，无论“核心线程”、等待队列处于什么样的状态！
        extendsPool.shutdown();
        // 当所有任务执行完成后，终止线程池的运行
        extendsPool.awaitTermination(Long.MAX_VALUE, TimeUnit.MINUTES);
    }

    /**
     * 这个就是测试用的线程
     * @author yinwenjie
     */
    private static class TestRunnable implements Runnable {

        /**
         * 记录任务的唯一编号，这样在日志中好做识别
         */
        private Integer index;

        public TestRunnable(int index) {
            this.index = index;
        }

        /**
         * @return the index
         */
        public Integer getIndex() {
            return index;
        }

        @Override
        public void run() {
            /*
             * 线程中，就只做一件事情：
             * 等待10秒钟的事件，以便模拟业务操作过程
             * */
            Thread currentThread  = Thread.currentThread();
            synchronized (currentThread) {
                try {
                    currentThread.wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace(System.out);
                }
            }
        }
    }
}

//        ————————————————
//        版权声明：本文为CSDN博主「说好不能打脸」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/yinwenjie/article/details/50600584