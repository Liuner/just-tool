package com.liugs.tool.utils.multithread.asynchronous;

import com.liugs.tool.constants.Console;
import lombok.SneakyThrows;

/**
 * @ClassName ThreadDemo1
 * @Description
 * @Author liugs
 * @Date 2020/12/1 15:04:04
 * @Copyright 2020 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class ThreadDemo1 implements Runnable {


    @SneakyThrows
    @Override
    public void run() {
        Thread thread = Thread.currentThread();
        Console.show(thread.getName() + " sleeping ....");
        Thread.sleep(5000);
        Console.show(this.getClass().getSimpleName() + " OVER");
    }
}
