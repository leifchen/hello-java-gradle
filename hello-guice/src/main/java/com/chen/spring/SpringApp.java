package com.chen.spring;

import com.chen.spring.manager.UserManager;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * SpringApp主程序入口
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
public class SpringApp {

    public static void main(String[] args) {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserManager userManager = (UserManager) beanFactory.getBean("userManager");
        userManager.test();
    }
}
