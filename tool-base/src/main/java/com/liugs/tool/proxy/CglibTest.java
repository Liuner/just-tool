package com.liugs.tool.proxy;

/**
 * @ClassName CglibTest
 * @Description CGLIB test
 * @Author liugs
 * @Date 2021/7/6 13:57:28
 */
public class CglibTest {
    public static void main(String[] args) {
        testCglib();
    }
    public static void testCglib() {
        CglibProxyExample cglibExample = new CglibProxyExample();
        HelloWord helloWord = (HelloWord) cglibExample.getProxy(HelloWord.class);
        helloWord.syHello();
    }
}
