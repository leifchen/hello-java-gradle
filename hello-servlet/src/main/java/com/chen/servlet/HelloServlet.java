package com.chen.servlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 自定义Servlet
 * <p>
 * @Author LeifChen
 * @Date 2019-04-23
 */
public class HelloServlet implements Servlet {

    @Override
    public void init(ServletConfig config) {
        System.out.println("Hello Servlet init");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) {
        System.out.println("Hello Servlet service method");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("Hello Servlet destroy");
    }
}
