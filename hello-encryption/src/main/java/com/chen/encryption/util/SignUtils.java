package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.interfaces.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 数字签名工具类
 *
 * @Author LeifChen
 * @Date 2018-12-03
 */
public class SignUtils {

    static final String STR = "Hello LeifChen";

    static final String RSA = "RSA";
    static final String DSA = "DSA";
    static final String EC = "EC";

    static final String MD5_WITH_RSA = "MD5withRSA";
    static final String SHA1_WITH_DSA = "SHA1withDSA";
    static final String SHA1_WITH_ECDSA = "SHA1withECDSA";

    /**
     * 基于 RSA 的数字签名
     */
    public static void jdkRSA() {
        try {
            // 构建密钥对
            KeyPair keyPair = generateSenderPublicKey(RSA, 512);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            // 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance(MD5_WITH_RSA);
            signature.initSign(privateKey);
            signature.update(STR.getBytes());
            byte[] result = signature.sign();
            System.out.println("JDK RSA Sign：" + Base64.encodeBase64String(result));

            // 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance(MD5_WITH_RSA);
            signature.initVerify(publicKey);
            signature.update(STR.getBytes());
            boolean bool = signature.verify(result);
            System.out.println("JDK RSA Verify: " + bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于 DSA 的数字签名
     */
    public static void jdkDSA() {
        try {
            // 构建密钥对
            KeyPair keyPair = generateSenderPublicKey(DSA, 512);
            DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
            DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

            // 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(DSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance(SHA1_WITH_DSA);
            signature.initSign(privateKey);
            signature.update(STR.getBytes());
            byte[] result = signature.sign();
            System.out.println("JDK DSA Sign：" + Base64.encodeBase64String(result));

            // 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance(DSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance(SHA1_WITH_DSA);
            signature.initVerify(publicKey);
            signature.update(STR.getBytes());
            boolean bool = signature.verify(result);
            System.out.println("JDK DSA Verify: " + bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于 ECDSA 的数字签名
     */
    public static void jdkECDSA() {
        try {
            // 构建密钥对
            KeyPair keyPair = generateSenderPublicKey(EC, 256);
            ECPublicKey dsaPublicKey = (ECPublicKey) keyPair.getPublic();
            ECPrivateKey dsaPrivateKey = (ECPrivateKey) keyPair.getPrivate();

            // 执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(dsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(EC);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance(SHA1_WITH_ECDSA);
            signature.initSign(privateKey);
            signature.update(STR.getBytes());
            byte[] result = signature.sign();
            System.out.println("JDK ECDSA Sign：" + Base64.encodeBase64String(result));

            // 验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(dsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance(EC);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance(SHA1_WITH_ECDSA);
            signature.initVerify(publicKey);
            signature.update(STR.getBytes());
            boolean bool = signature.verify(result);
            System.out.println("JDK ECDSA Verify: " + bool);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 构建密钥对
     *
     * @return 构建完的公钥私钥
     * @throws NoSuchAlgorithmException
     */
    private static KeyPair generateSenderPublicKey(String type, int keysize) throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(type);
        senderKeyPairGenerator.initialize(keysize);
        return senderKeyPairGenerator.generateKeyPair();
    }
}
