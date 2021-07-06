package com.liugs.tool.other.proxy;

/**
 * @ClassName ProxyTester
 * @Description 代理测试类
 * @Author liugs
 * @Date 2021/7/6 8:48
 */
public class ProxyTester {

    public static void main(String[] args) {
        testCglib();

    }

    public static void testCglib() {
        CglibProxyExample cpe = new CglibProxyExample();
        HelloWord helloWord = (HelloWord) cpe.getProxy(HelloWord.class);
        helloWord.sayHello();
    }
}
