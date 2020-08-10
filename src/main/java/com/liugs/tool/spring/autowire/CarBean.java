package com.liugs.tool.spring.autowire;


import com.liugs.tool.constants.Console;

/**
 * @ClassName CarBean
 * @Description 自动注入测试
 * @Author liugs
 * @Date 2020/8/7 17:08:29
 */
public class CarBean {

    private Wheel wheel;
    private String brand;

    public CarBean(Wheel wheel, String no) {
        this.wheel = wheel;
        Console.show("CarBean constructor：" + no);
    }

    public void run() {
        Console.show("star engine");
        wheel.run();
    }

    public void setWheel(Wheel wheel) {
        this.wheel = wheel;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
