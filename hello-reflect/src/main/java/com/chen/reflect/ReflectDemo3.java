package com.chen.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * ReflectDemo3
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
public class ReflectDemo3 {

    public static void main(String[] args) {
        List list1 = new ArrayList();
        List<String> list2 = new ArrayList<>();
        list2.add("hello");
        // 编译期检查错误
        /* list2.add(100); */

        Class c1 = list1.getClass();
        Class c2 = list2.getClass();
        // true：说明编译之后集合的泛型是去泛型化的
        System.out.println(c1 == c2);

        // 使用反射可以绕过编译期的泛型检查
        try {
            Method method = c2.getMethod("add", Object.class);
            method.invoke(list2, 100);
            System.out.println(list2);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
