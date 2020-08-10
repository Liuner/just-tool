package com.liugs.tool.utils.multithread.queue;

import com.liugs.tool.constants.Console;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName ConsumerRunnable
 * @Description 消费者线程
 * @Author liugs
 * @Date 2020/7/14 15:59:31
 */
public class ConsumerRunnable implements Runnable {

    private LinkedTransferQueue<PriorityBlockingQueueDemo.TempDemo> linkedQueue;

    public ConsumerRunnable(LinkedTransferQueue<PriorityBlockingQueueDemo.TempDemo> linkedQueue) {
        this.linkedQueue = linkedQueue;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (!currentThread.isInterrupted()) {
            try {
                // 等待，直到从LinkedTransferQueue队列中得到一个元素
                PriorityBlockingQueueDemo.TempDemo targetObject = this.linkedQueue.take();
                Console.show("消费者线程（" + currentThread.getName() + "）取得targetObject.index = " + targetObject.getIndex());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
