package com.chen.dynamic;

/**
 * 目标类的实现
 *
 * @Author LeifChen
 * @Date 2018-09-27
 */
public class TargetImpl implements Target {
    @Override
    public int test(int i) {
        return i + 1;
    }
}
