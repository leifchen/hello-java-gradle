package com.chen.reflect;

import lombok.Getter;
import lombok.Setter;

/**
 * Target
 *
 * @Author LeifChen
 * @Date 2018-09-28
 */
@Setter
@Getter
public class Target {

    private String name;
    private int age;

    public Target() {
        this.name = "LeifChen";
    }
}
