package com.chen.encryption.util;

import org.junit.Test;

/**
 * 对称加密工具的测试类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class SymmetricalEncryptionUtilsTest {

    static final String str = "Hello LeifChen";

    @Test
    public void jdkDES() {
        System.out.println("JDK DES Encrypt: " + SymmetricalEncryptionUtils.jdkDES(str));
    }

    @Test
    public void bcDES() {
        System.out.println("BC DES Encrypt: " + SymmetricalEncryptionUtils.bcDES(str));
    }

    @Test
    public void jdk3DES() {
        System.out.println("JDK 3DES Encrypt: " + SymmetricalEncryptionUtils.jdk3DES(str));
    }

    @Test
    public void bc3DES() {
        System.out.println("BC 3DES Encrypt: " + SymmetricalEncryptionUtils.bc3DES(str));
    }

    @Test
    public void jdkAES() {
        System.out.println("JDK DES Encrypt: " + SymmetricalEncryptionUtils.jdkAES(str));
    }

    @Test
    public void bcAES() {
        System.out.println("BC DES Encrypt: " + SymmetricalEncryptionUtils.bcAES(str));
    }

    @Test
    public void jdkPBE() {
        System.out.println("JDK PBE Encrypt: " + SymmetricalEncryptionUtils.jdkPBE(str));
    }

    @Test
    public void bcPBE() {
        System.out.println("BC PBE Encrypt: " + SymmetricalEncryptionUtils.bcPBE(str));
    }
}