package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

/**
 * Base64 加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class Base64Utils {

    /**
     * 使用 JDK Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String jdkBase64(String str) {
        return new BASE64Encoder().encode(str.getBytes());
    }

    /**
     * 使用 Commons Codec Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String commonsCodecBase64(String str) {
        return new String(Base64.encodeBase64(str.getBytes()));
    }

    /**
     * 使用 Bouncy Castle Base64 加密
     *
     * @param str 明文
     * @return Base64 密文
     */
    public static String bouncyCastleBase64(String str) {
        return new String(org.bouncycastle.util.encoders.Base64.encode(str.getBytes()));
    }
}
