package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.Security;

/**
 * 加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-29
 */
public class EncoderUtils {

    static final String KEY = "aa";

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

    /**
     * 使用 JDK MD5 加密
     *
     * @param str 明文
     * @return MD5 密文
     */
    public static String jdkMD5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return Hex.encodeHexString(md.digest(str.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 Commons Codec MD5 加密
     *
     * @param str 明文
     * @return MD5 密文
     */
    public static String commonsCodecMD5(String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * 使用 Bouncy Castle MD5 加密
     *
     * @param str 明文
     * @return MD5 密文
     */
    public static String bouncyCastleMD5(String str) {
        Digest digest = new MD5Digest();
        digest.update(str.getBytes(), 0, str.getBytes().length);
        byte[] md5Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(md5Bytes, 0);
        return org.bouncycastle.util.encoders.Hex.toHexString(md5Bytes);
    }

    /**
     * 使用 JDK SHA-1 加密
     *
     * @param str 明文
     * @return SHA-1 密文
     */
    public static String jdkSHA1(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(str.getBytes());
            return Hex.encodeHexString(md.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 Commons Codec SHA-1 加密
     *
     * @param str 明文
     * @return SHA-1 密文
     */
    public static String commonsCodecSHA1(String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * 使用 Bouncy Castle SHA-1 加密
     *
     * @param str 明文
     * @return SHA-1 密文
     */
    public static String bouncyCastleSHA1(String str) {
        Digest digest = new SHA1Digest();
        digest.update(str.getBytes(), 0, str.getBytes().length);
        byte[] sha1Bytes = new byte[digest.getDigestSize()];
        digest.doFinal(sha1Bytes, 0);
        return org.bouncycastle.util.encoders.Hex.toHexString(sha1Bytes);
    }

    /**
     * 使用 Bouncy Castle SHA-224 加密
     *
     * @param str 明文
     * @return SHA-224 密文
     */
    public static String bouncyCastleSHA224(String str) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            MessageDigest md = MessageDigest.getInstance("SHA224");
            md.update(str.getBytes());
            return Hex.encodeHexString(md.digest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 JDK HmacMD5 加密
     *
     * @param str 明文
     * @return HmacMD5 密文
     */
    public static String jdkHmacMD5(String str) {
        try {
            byte[] key = KEY.getBytes();
            SecretKey restoreKey = new SecretKeySpec(key, "HmacMD5");
            Mac mac = Mac.getInstance(restoreKey.getAlgorithm());
            mac.init(restoreKey);
            byte[] hmacMD5Bytes = mac.doFinal(str.getBytes());
            return Hex.encodeHexString(hmacMD5Bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 Commons Codec HmacMD5 加密
     *
     * @param str 明文
     * @return HmacMD5 密文
     */
    public static String commonsCodecHmacMD5(String str) {
        Mac mac = HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_MD5, KEY.getBytes());
        return Hex.encodeHexString(mac.doFinal(str.getBytes()));
    }

    /**
     * 使用 Bouncy Castle HmacMD5 加密
     *
     * @param str 明文
     * @return HmacMD5 密文
     */
    public static String bouncyCastleHmacMD5(String str) {
        HMac hMac = new HMac(new MD5Digest());
        hMac.init(new KeyParameter(KEY.getBytes()));
        hMac.update(str.getBytes(), 0, str.getBytes().length);
        byte[] hmacMD5Bytes = new byte[hMac.getMacSize()];
        hMac.doFinal(hmacMD5Bytes, 0);
        return org.bouncycastle.util.encoders.Hex.toHexString(hmacMD5Bytes);
    }
}
