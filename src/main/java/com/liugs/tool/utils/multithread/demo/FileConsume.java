package com.liugs.tool.utils.multithread.demo;

import com.liugs.tool.constants.Console;

import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName FileConsume
 * @Description 文件消费
 * @Author liugs
 * @Date 2020/7/22 16:45:52
 */
public class FileConsume implements Runnable {

    private LinkedTransferQueue<FileCarry> linkedTransferQueue;

    public FileConsume(LinkedTransferQueue<FileCarry> linkedTransferQueue) {
        this.linkedTransferQueue = linkedTransferQueue;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();

        while (!currentThread.isInterrupted()) {
            try {
                // 等待，直到从LinkedTransferQueue队列中得到一个元素
                FileCarry fileCarry = this.linkedTransferQueue.take();
                Console.show("消费者线程（" + currentThread.getName() + "）取得fileCarry.index = " + fileCarry.getIndex() +
                        "："+ fileCarry.getContent());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace(System.out);
            }
        }
    }
}
