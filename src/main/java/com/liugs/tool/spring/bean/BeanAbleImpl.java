package com.liugs.tool.spring.bean;

/**
 * @ClassName BeanAbleImpl
 * @Description
 * @Author liugs
 * @Date 2020/8/7 15:53:37

 */
public class BeanAbleImpl implements BeanAble {

    private Integer beanId = 2;

    private String beanName = "BeanAbleImpl";

    public Integer getBeanId() {
        return beanId;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanId(Integer beanId) {
        this.beanId = beanId;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
