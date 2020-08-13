package com.liugs.tool.spring.annotations;


import com.liugs.tool.constants.Console;

import javax.annotation.Resource;

/**
 * @ClassName Computer
 * @Description 注解测试
 * @Author liugs
 * @Date 2020/8/7 17:44:11

 */
public class Computer {
    private String brand;
    private String system;

/*    @Autowired*/
    private Core core;

/*    public Computer(Core core) {
        this.core = core;
    }*/

    //@Autowired 可以使用在set方法上
//    @Autowired
/*    public void setCore(Core core) {
        this.core = core;
    }*/

    @Resource
    public void setCore(Core core) {
        this.core = core;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public void star() {
        Console.show("brand：" + brand + "，system：" + system);
        core.model();
    }

    public void init() {
       Console.show("Computer bean init");
    }

    public void destroy() {
        Console.show("Computer bean destroy");
    }
}
