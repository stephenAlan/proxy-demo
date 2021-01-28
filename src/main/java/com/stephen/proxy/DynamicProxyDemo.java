package com.stephen.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by ssc on 2021-01-28 9:54 .
 * Description: jdk动态代理实现接口调用
 * Mybatis底层就是这样实现的，同一个Mapper中的方法不可重载
 */
public class DynamicProxyDemo {

    public static void main(String[] args) {
        // 定义处理方法的执行器
        InvocationHandler invocationHandler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getName().equals("morning")) {
                    System.out.println("Good morning " + args[0]);
                }
                return null;
            }
        };

        // 创建动态代理类
        Hello hello = (Hello) Proxy.newProxyInstance(
                Hello.class.getClassLoader(),
                new Class[]{Hello.class},
                invocationHandler);
        hello.morning("World");
    }

}

interface Hello {
    void morning(String name);
}