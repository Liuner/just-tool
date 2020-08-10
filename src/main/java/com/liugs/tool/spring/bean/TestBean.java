package com.liugs.tool.spring.bean;


import com.liugs.tool.constants.Console;

/**
 * @ClassName TestBean
 * @Description 测试Bean
 * @Author liugs
 * @Date 2020/8/7 14:15:06

 */
public class TestBean implements BeanAble {

    private Integer beanId = 1;

    private String beanName = "TestBean";

    @Override
    public Integer getBeanId() {
        return beanId;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void init(){
        Console.show("Bean is going through init.");
    }
    public void destroy(){
        Console.show("Bean will destroy now.");
    }
}
