package com.liugs.tool.utils.multithread;

import com.liugs.tool.constants.Console;

/**
 * @ClassName ThreadDemo
 * @Description
 * @Author liugs
 * @Date 2020/7/13 14:48:45
 */
public class ThreadDemo extends Thread {

    private Thread thread;
    private String threadName;

    ThreadDemo(String threadName) {
        this.threadName = threadName;
        Console.show("Creating " + threadName);
    }

    @Override
    public void run() {
        Console.show("===============Running " + threadName + "===============");
        try {
            for(int i = 4; i > 0; i--) {
                Console.show("Thread：" + threadName + "，" + i);

                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            Console.show("Thread " +  threadName + " interrupted.");
        }
        Console.show("===============End " + threadName + "===============");
    }

    @Override
    public void start() {
        Console.show("Starting " +  threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }
}
