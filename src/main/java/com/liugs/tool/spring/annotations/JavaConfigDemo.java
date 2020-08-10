package com.liugs.tool.spring.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName JavaConfigDemo
 * @Description
 * @Author liugs
 * @Date 2020/8/10 11:06:27
 */
@Configuration
public class JavaConfigDemo {

    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Computer computer() {
        Computer computer = new Computer();
        computer.setBrand("AUDI");
        computer.setSystem("Massa Lydia");
        return computer;
    }

    //这种方式去依赖的话 需要构造函数
/*    @Bean
    public Computer computer() {
        return new Computer(core());
    }*/

    @Bean
    public Core core() {
        return new Core();
    }
}
