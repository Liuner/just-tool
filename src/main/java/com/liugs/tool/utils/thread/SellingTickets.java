package com.liugs.tool.utils.thread;


import com.liugs.tool.constants.Console;

/**
 * @ClassName SellingTickets
 * @Description
 * @Author liugs
 * @Date 2020/7/14 10:44:39
 */
public class SellingTickets implements Runnable {

    private Integer tickets = 100;

    @Override
    public void run() {
        while (tickets > 0) {
            synchronized (tickets) {
                Console.show(Thread.currentThread().getName() + " is selling，" + "There are " + --tickets + " tickets left.");
            }
            //买完一张就睡，让其他线程也买，不然执行太快看不出效果😄
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Console.show("The tickets are sold out!");
    }
}
