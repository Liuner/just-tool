package com.liugs.tool.utils.multithread.queue;

import com.liugs.tool.constants.Console;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName ProducerRunnable
 * @Description 生产者线程
 * @Author liugs
 * @Date 2020/7/14 15:58:17
 */
public class ProducerRunnable implements Runnable {

    private LinkedTransferQueue<PriorityBlockingQueueDemo.TempDemo> linkedQueue;

    public ProducerRunnable(LinkedTransferQueue<PriorityBlockingQueueDemo.TempDemo> linkedQueue) {
        this.linkedQueue = linkedQueue;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        for(int index = 1 ; ; index++) {
            try {
                // 向LinkedTransferQueue队列插入一个新的元素
                // 然后生产者线程就会等待，直到有一个消费者将这个元素从队列中取走
                Thread.sleep(1000);
                this.linkedQueue.transfer(new PriorityBlockingQueueDemo.TempDemo(index));
                Console.show("生产者线程（" + currentThread.getName() + "）添加targetObject.index = " + index);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
