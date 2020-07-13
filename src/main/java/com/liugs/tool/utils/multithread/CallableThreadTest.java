package com.liugs.tool.utils.multithread;

import com.liugs.tool.constants.Console;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @ClassName CallableThreadTest
 * @Description
 * @Author liugs
 * @Date 2020/7/13 14:59:17
 */
public class CallableThreadTest implements Callable<Integer> {

    public static void main(String[] args)
    {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<Integer> ft = new FutureTask<>(ctt);
        for (int i = 0; i < 100; i++)
        {
            Console.show(Thread.currentThread().getName()+" 的循环变量i的值"+i);
            if (i == 20) {
                new Thread(ft,"有返回值的线程").start();
            }
        }
        try {
            Console.show("子线程的返回值："+ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Integer call() {
        int i = 0;
        for(; i < 100; i++) {
            Console.show(Thread.currentThread().getName()+" "+i);
        }
        return i;
    }
}
