package com.liugs.tool.utils.multithread.demo;

import com.liugs.tool.constants.Console;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;

/**
 * @ClassName FileReader
 * @Description 文件读取
 * @Author liugs
 * @Date 2020/7/22 16:44:50
 */
public class FileReader implements Runnable {

    private LinkedTransferQueue<FileCarry> linkedTransferQueue;
    private BufferedReader bufferedReader;

    public FileReader(LinkedTransferQueue<FileCarry> linkedTransferQueue, BufferedReader bufferedReader) {
        this.linkedTransferQueue = linkedTransferQueue;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run() {
        Thread currentThread = Thread.currentThread();
        Console.show("文件读取线程（" + currentThread.getName() + "）启动");
        String str;
        Integer index = 0;
        try {
            while ((str = bufferedReader.readLine()) != null) {
                this.linkedTransferQueue.transfer(new FileCarry(index++, str));
//                Console.show("文件读取线程（" + currentThread.getName() + "）fileCarry.index = " + index);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}