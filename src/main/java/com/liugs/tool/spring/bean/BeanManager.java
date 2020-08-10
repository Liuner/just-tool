package com.liugs.tool.spring.bean;

import com.liugs.tool.constants.Console;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName BeanManager
 * @Description bean管理器
 * @Author liugs
 * @Date 2020/8/7 14:17:58 */
@Component
public class BeanManager {

    private final Map<Integer, BeanAble> beanMap = new ConcurrentHashMap(16);

    public BeanAble getBean(Integer beanId) {
        BeanAble bean = beanMap.get(beanId);
        if (null == bean) {
            Console.show("not found bean with beanId=" + beanId);
        }
        return bean;
    }

    public void registerBean(BeanAble beanAble) {
        Assert.notNull(beanAble, "register bean can not be null");
        Assert.notNull(beanAble.getBeanId(), "beanId can not be null");
        BeanAble bean = beanMap.get(beanAble.getBeanId());
        if (null != bean) {
            System.out.println("this bean has exist");
            return;
        }
        beanMap.put(beanAble.getBeanId(), beanAble);
        Console.show(beanAble.getBeanName() + " register success");
    }
}
