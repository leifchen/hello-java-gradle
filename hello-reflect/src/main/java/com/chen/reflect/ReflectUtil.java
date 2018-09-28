package com.chen.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * ReflectUtil
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
public class ReflectUtil {

    /**
     * 打印类的构造函数
     *
     * @param obj
     */
    public static void printConstrutorMethods(Object obj) {
        System.out.println("类的构造函数：");

        Class c = obj.getClass();
        Constructor[] constructors = c.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            // 构造函数的名称
            System.out.print(constructor.getName() + "(");
            // 构造函数的参数类型
            Class[] paramTypes = constructor.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                System.out.print(paramTypes[i].getSimpleName());
                if (i < paramTypes.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

    /**
     * 打印类的方法
     *
     * @param obj
     */
    public static void printMethods(Object obj) {
        System.out.println("类的方法：");

        Class c = obj.getClass();
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            // 方法的返回值类型的类类型
            Class rtnType = method.getReturnType();
            System.out.print(rtnType.getSimpleName() + " ");
            // 方法名称
            System.out.print(method.getName() + "(");
            // 方法的参数类型
            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                System.out.print(paramTypes[i].getSimpleName());
                if (i < paramTypes.length - 1) {
                    System.out.print(",");
                }
            }
            System.out.println(")");
        }
    }

    /**
     * 打印类的成员变量
     *
     * @param obj
     */
    public static void printFields(Object obj) {
        System.out.println("类的成员变量：");

        Class c = obj.getClass();
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 成员变量的类型的类类型
            Class fieldType = field.getType();
            String typeName = fieldType.getSimpleName();
            // 成员变量的名称
            String fieldName = field.getName();
            System.out.println(typeName + " " + fieldName);
        }
    }
}
