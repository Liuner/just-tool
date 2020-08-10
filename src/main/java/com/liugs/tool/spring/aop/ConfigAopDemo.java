package com.liugs.tool.spring.aop;

import com.liugs.tool.constants.Console;

/**
 * @ClassName ConfigAopDemo
 * @Description
 * @Author liugs
 * @Date 2020/8/10 16:53:59
 */
public class ConfigAopDemo {

    private String name;
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void printThrowException() {
        Console.show("Exception raised");
        throw new IllegalArgumentException();
    }
}
