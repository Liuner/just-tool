package com.liugs.tool.interceptor;

import java.lang.reflect.Method;

/**
 * @ClassName InterceptorImpl
 * @Description 拦截器实现类
 * @Author liugs
 * @Date 2021/7/6 22:46
 */
public class InterceptorImpl implements Interceptor{

    @Override
    public boolean before(Object proxy, Object target, Method method, Object[] args) {
        return false;
    }

    @Override
    public boolean around(Object proxy, Object target, Method method, Object[] args) {
        return false;
    }

    @Override
    public boolean after(Object proxy, Object target, Method method, Object[] args) {
        return false;
    }
}
