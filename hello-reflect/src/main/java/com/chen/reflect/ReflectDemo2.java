package com.chen.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ReflectDemo2
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
public class ReflectDemo2 {

    public static void main(String[] args) {
        MyClass my = new MyClass();
        Class c = my.getClass();
        try {
            Method method1 = c.getMethod("print");
            method1.invoke(my);

            System.out.println("=====");
            Method method2 = c.getMethod("print", int.class, int.class);
            method2.invoke(my, 10, 20);

            System.out.println("=====");
            Method method3 = c.getMethod("print", String.class, String.class);
            method3.invoke(my, "hello", "reflect");
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

class MyClass {
    public void print() {
        System.out.println("Print");
    }

    public void print(int a, int b) {
        System.out.println(a + b);
    }

    public void print(String a, String b) {
        System.out.println(a.toUpperCase() + " " + b.toUpperCase());
    }
}
