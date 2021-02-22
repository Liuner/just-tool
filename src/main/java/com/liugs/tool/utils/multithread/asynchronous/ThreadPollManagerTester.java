package com.liugs.tool.utils.multithread.asynchronous;

import com.liugs.tool.constants.Console;

/**
 * @ClassName ThreadPollManagerTester
 * @Description
 * @Author liugs
 * @Date 2020/12/1 15:06:21
 * @Copyright 2020 www.tydic.com Inc. All rights reserved.
 * 注意 本内容仅限于北京天源迪科信息技术有限公司内部传阅，禁止外泄以及用于其他商业目的
 */
public class ThreadPollManagerTester {

    public static void main(String[] args) {

        ThreadDemo1 demo1 = new ThreadDemo1();

        ThreadPoolManager poolManager = ThreadPoolManager.getInstance();
        poolManager.addTask(demo1);
        poolManager.addTask(demo1);
        Console.show("Main thread over");
        poolManager.shutdown();
    }
}
