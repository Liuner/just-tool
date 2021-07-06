package com.liugs.tool.proxy;

import com.liugs.tool.base.Console;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName cglibExample
 * @Description CGLIB代理
 * @Author liugs
 * @Date 2021/7/6 13:49:32
 */
public class CglibProxyExample implements MethodInterceptor {

    /**
     * 描述 获取代理对象
     * @param cls
     * @return java.lang.Object
     * @author liugs
     * @date 2021/7/6 13:51:10
     */
    public Object getProxy(Class cls) {
        //CGLIB 增强器
        Enhancer enhancer = new Enhancer();
        //指定此代理的父类（被代理的类）
        enhancer.setSuperclass(cls);
        //当代理的中的方法被调用后需要指明跳转到的自定义方法拦截器
        enhancer.setCallback(this);
        //返回一个被代理类的子类
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Console.show("调用真实方法前");
        Object result = methodProxy.invokeSuper(obj,args);
        Console.show("调用真实方法后");
        return result;
    }
}
