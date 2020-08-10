package com.liugs.tool.spring.bean;

import com.liugs.tool.constants.Console;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @ClassName BeanPostProcess
 * @Description bean处理器
 * @Author liugs
 * @Date 2020/8/7 14:25:50

 */
public class BeanProcess implements BeanPostProcessor {

    private BeanManager beanManager;
    private DITest diTest;

    public BeanProcess(BeanManager beanManager, DITest diTest) {
        this.beanManager = beanManager;
        this.diTest = diTest;
        diTest.print();
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       Console.show("BeforeInitialization：" + beanName + " is about to init");
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanAble) {
            Console.show("AfterInitialization：" + beanName + " init successed");
            beanManager.registerBean((BeanAble) bean);
        }
        Console.show("================================");
        return null;
    }

/*    ========================用来测试 set方法实现依赖注入===========================*/
/*    public void setBeanManager(BeanManager beanManager) {
        this.beanManager = beanManager;
    }

    public void setDiTest(DITest diTest) {
        this.diTest = diTest;
    }*/
/*    ========================用来测试 set方法实现依赖注入===========================*/
}
