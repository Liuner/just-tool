package com.liugs.tool.utils.multithread.demo;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import cn.hutool.core.thread.ThreadUtil;
import com.liugs.tool.constants.Console;

import java.io.*;
import java.util.concurrent.*;

/**
 * @ClassName FIileFactory
 * @Description
 * @Author liugs
 * @Date 2020/7/22 16:46:59
 */
public class FIileFactory {

    public static void main(String[] args) {
        LinkedTransferQueue<FileCarry> linkedTransferQueue = new LinkedTransferQueue<>();

        //定义一个线程池
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNamePrefix("file-demo-").build();

        ExecutorService pool = new ThreadPoolExecutor(3, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        BufferedReader reader = null;
        try {
            File file = new File("E:\\Liunuer\\Documents\\code\\just-tool\\src\\main\\java\\com\\liugs\\tool\\utils\\multithread\\demo\\ReadTest.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "GBK");
            reader = new BufferedReader(inputStreamReader);

            //使用Resource来获取InputStream
            /*Resource resource = new ClassPathResource("application.properties");
            InputStream inputStreamReader1 = resource.getInputStream();
            InputStreamReader inputStreamReader2 = new InputStreamReader(inputStreamReader1, "GBK");
            reader = new BufferedReader(inputStreamReader2);*/
        } catch (IOException e) {
            e.printStackTrace();
        }

        FileReader fileReader = new FileReader(linkedTransferQueue, reader);
        FileConsume fileConsume = new FileConsume(linkedTransferQueue);
        FileConsume fileConsume2 = new FileConsume(linkedTransferQueue);

        /*pool.submit(fileReader);
        pool.submit(fileConsume);
        pool.submit(fileConsume2);

        pool.shutdown();*/

        ThreadUtil.execAsync(fileReader);
        ThreadUtil.execAsync(fileConsume);
        ThreadUtil.execAsync(fileConsume2);

        Console.show("over");
    }
}
