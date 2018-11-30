package com.chen.encryption.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 消息摘要加密工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-11-29
 */
public class MessageDigestUtilsTest {

    static final String str = "Hello LeifChen";

    @Test
    public void MD5() {
        String jdkMD5 = MessageDigestUtils.jdkMD5(str);
        String ccMD5 = MessageDigestUtils.commonsCodecMD5(str);
        String bcMD5 = MessageDigestUtils.bouncyCastleMD5(str);
        System.out.println("MD5: " + MessageDigestUtils.jdkMD5(str));
        Assert.assertEquals(jdkMD5, ccMD5);
        Assert.assertEquals(jdkMD5, bcMD5);
    }

    @Test
    public void SHA1() {
        String jdkSHA1 = MessageDigestUtils.jdkSHA1(str);
        String ccSHA1 = MessageDigestUtils.commonsCodecSHA1(str);
        String bcSHA1 = MessageDigestUtils.bouncyCastleSHA1(str);
        System.out.println("SHA-1: " + MessageDigestUtils.jdkSHA1(str));
        System.out.println("SHA-224: " + MessageDigestUtils.bouncyCastleSHA224(str));
        Assert.assertEquals(jdkSHA1, ccSHA1);
        Assert.assertEquals(jdkSHA1, bcSHA1);
    }

    @Test
    public void HmacMD5() {
        String jdkHmacMD5 = MessageDigestUtils.jdkHmacMD5(str);
        String ccHmacMD5 = MessageDigestUtils.commonsCodecHmacMD5(str);
        String bcHmacMD5 = MessageDigestUtils.bouncyCastleHmacMD5(str);
        System.out.println("HmacMD5: " + MessageDigestUtils.jdkHmacMD5(str));
        Assert.assertEquals(jdkHmacMD5, ccHmacMD5);
        Assert.assertEquals(jdkHmacMD5, bcHmacMD5);
    }
}