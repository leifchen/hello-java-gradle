package com.chen.encryption.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * 加密工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-11-29
 */
public class EncoderUtilsTest {

    static final String str = "Hello LeifChen";

    @Test
    public void base64() {
        String jdkBase64 = EncoderUtils.jdkBase64(str);
        String ccBase64 = EncoderUtils.commonsCodecBase64(str);
        String bcBase64 = EncoderUtils.bouncyCastleBase64(str);
        System.out.println("Base64: " + EncoderUtils.jdkBase64(str));
        Assert.assertEquals(jdkBase64, ccBase64, bcBase64);
    }

    @Test
    public void MD5() {
        String jdkMD5 = EncoderUtils.jdkMD5(str);
        String ccMD5 = EncoderUtils.commonsCodecMD5(str);
        String bcMD5 = EncoderUtils.bouncyCastleMD5(str);
        System.out.println("MD5: " + EncoderUtils.jdkMD5(str));
        Assert.assertEquals(jdkMD5, ccMD5, bcMD5);
    }

    @Test
    public void SHA1() {
        String jdkSHA1 = EncoderUtils.jdkSHA1(str);
        String ccSHA1 = EncoderUtils.commonsCodecSHA1(str);
        String bcSHA1 = EncoderUtils.bouncyCastleSHA1(str);
        System.out.println("SHA-1: " + EncoderUtils.jdkSHA1(str));
        System.out.println("SHA-224: " + EncoderUtils.bouncyCastleSHA224(str));
        Assert.assertEquals(jdkSHA1, ccSHA1, bcSHA1);
    }

    @Test
    public void HmacMD5() {
        String jdkHmacMD5 = EncoderUtils.jdkHmacMD5(str);
        String ccHmacMD5 = EncoderUtils.commonsCodecHmacMD5(str);
        String bcHmacMD5 = EncoderUtils.bouncyCastleHmacMD5(str);
        System.out.println("HmacMD5: " + EncoderUtils.jdkHmacMD5(str));
        Assert.assertEquals(jdkHmacMD5, ccHmacMD5, bcHmacMD5);
    }
}