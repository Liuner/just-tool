package com.liugs.tool.utils.multithread;

import com.liugs.tool.constants.Console;

/**
 * @ClassName UTaskRunnable
 * @Description
 * @Author liugs
 * @Date 2020/7/13 15:13:40
 */
public class UTaskRunnable implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            Console.show(Thread.currentThread().getName() + "：" + i);
        }
    }
}
