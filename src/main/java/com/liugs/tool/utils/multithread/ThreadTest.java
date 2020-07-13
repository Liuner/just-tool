package com.liugs.tool.utils.multithread;

/**
 * @ClassName TheadTest
 * @Description
 * @Author liugs
 * @Date 2020/7/13 14:38:35
 */
public class ThreadTest {

    public static void main(String[] args) {
//        runnableTest();
        threadTest();
    }

    private static void runnableTest() {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();

        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }

    private static void threadTest() {
        ThreadDemo td = new ThreadDemo("Thread-1");
        td.start();
    }
}
