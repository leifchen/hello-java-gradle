package com.chen.encryption.util;

import org.junit.Test;

/**
 * Base64 加密工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class Base64UtilsTest {

    static final String str = "Hello LeifChen";

    @Test
    public void jdkBase64() {
        System.out.println("JDK Base64: " + Base64Utils.jdkBase64(str));
    }

    @Test
    public void commonsCodecBase64() {
        System.out.println("CC  Base64: " + Base64Utils.commonsCodecBase64(str));
    }

    @Test
    public void bouncyCastleBase64() {
        System.out.println("BC  Base64: " + Base64Utils.bouncyCastleBase64(str));
    }
}