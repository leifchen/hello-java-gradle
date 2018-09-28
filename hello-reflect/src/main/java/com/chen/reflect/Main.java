package com.chen.reflect;

/**
 * Main
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
public class Main {

    public static void main(String[] args) {
        String s = "hello";
        System.out.println("类的全称是：" + s.getClass().getName());
        ReflectUtil.printConstrutorMethods(s);
        ReflectUtil.printMethods(s);
        ReflectUtil.printFields(s);

        System.out.println("=====================================");

        int i = 100;
        System.out.println("类的全称是：" + Integer.class.getName());
        ReflectUtil.printConstrutorMethods(i);
        ReflectUtil.printMethods(i);
        ReflectUtil.printFields(i);
    }
}
