package com.liugs.tool.other.proxy;

import com.liugs.tool.base.Console;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CglibProxyExample
 * @Description cglb代理
 * @Author liugs
 * @Date 2021/7/6 8:36
 */
public class CglibProxyExample implements MethodInterceptor {

    /**
     * 描述 生产代理对象
     * @param cls
     * @return java.lang.Object
     * @author liugs
     * @date 2021/7/6 8:40
     */
    public Object getProxy(Class cls) {
        //CGLB enhancer 增强对象
        Enhancer enhancer = new Enhancer();
        //设置增强类型
        enhancer.setSuperclass(cls);
        //定义代理逻辑对象为当前对象，要求当前对象实现MethodInterceptor方法
        enhancer.setCallback(this);
        //生产并返回代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Console.show("调用真实对象前");
        //CGLB反射真实对象方法
        Object result = methodProxy.invokeSuper(proxy, args);
        Console.show("调用真实对象后");
        return result;
    }
}
