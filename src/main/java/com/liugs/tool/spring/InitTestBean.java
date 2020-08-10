package com.liugs.tool.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName InitTestBean
 * @Description 测试BeanPostProcessor
 * @Author liugs
 * @Date 2020/8/7 11:07:35
 */
public class InitTestBean implements BeanPostProcessor {


    //初始化前的处理
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeforeInitialization：" + beanName);
        return bean;
    }

    //初始化后的处理
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("AfterInitialization ：" + beanName);
        return bean;
    }
}
