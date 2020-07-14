package com.liugs.tool.utils.multithread.queue;

import com.liugs.tool.constants.Console;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName PriorityBlockingQueueDemo
 * @Description
 * @Author liugs
 * @Date 2020/7/14 15:32:40
 */
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        PriorityBlockingQueue<TempDemo> priorityQueue = new PriorityBlockingQueue<TempDemo>();
        priorityQueue.put(new TempDemo(-5));
        priorityQueue.put(new TempDemo(5));
        priorityQueue.put(new TempDemo(-1));
        priorityQueue.put(new TempDemo(1));

        // 第一个元素是5
        // 实际上在还没有执行priorityQueue.poll()语句的时候，队列中的第二个元素不一定是1
        TempDemo targetTempObject = priorityQueue.poll();
        Console.show("tempObject.index = " + targetTempObject.getIndex());
        // 第二个元素是1
        targetTempObject = priorityQueue.poll();
        Console.show("tempObject.index = " + targetTempObject.getIndex());
        // 第三个元素是-1
        targetTempObject = priorityQueue.poll();
        Console.show("tempObject.index = " + targetTempObject.getIndex());
        // 第四个元素是-5
        targetTempObject = priorityQueue.poll();
        Console.show("tempObject.index = " + targetTempObject.getIndex());

//        原文链接：https://blog.csdn.net/yinwenjie/article/details/50577325
    }

    public static class TempDemo implements Comparable<TempDemo> {
        private Integer index;

        TempDemo(Integer index) {
            this.index = index;
        }

        public Integer getIndex() {
            return index;
        }

        @Override
        public int compareTo(TempDemo o) {
            return o.getIndex() - this.index;
        }
    }
}
