package com.chen.reflect;

/**
 * ReflectDemo
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
public class ReflectDemo {

    public static void main(String[] args) {
        Target target = new Target();

        // 1.任何一个类都有一个隐含的静态成员变量 class
        Class c1 = Target.class;

        // 2.通过实例对象的 getClass() 方法
        Class c2 = target.getClass();

        // 3.通过 Class.forName ("类名字符串")
        Class c3 = null;
        try {
            c3 = Class.forName("com.chen.reflect.Target");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(c1 == c2);
        System.out.println(c2 == c3);

        try {
            // 需要无参数的构造方法
            Target target2 = (Target) c1.newInstance();
            System.out.println(target2.getName());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
