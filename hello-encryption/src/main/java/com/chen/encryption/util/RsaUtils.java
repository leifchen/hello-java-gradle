package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 基于 RSA 算法的非对称加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class RsaUtils {
    static final String RSA = "RSA";
    static final String STR = "Hello LeifChen";

    public static void jdkRSA() {
        try {
            // 构建密钥对
            KeyPair keyPair = generateSenderPublicKey();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();

            // 1.私钥加密、公钥解密——加密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(RSA);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, privateKey);
            byte[] result = cipher.doFinal(STR.getBytes());
            System.out.println("私钥加密、公钥解密——加密：" + Base64.encodeBase64String(result));

            // 2.私钥加密、公钥解密——解密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance(RSA);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, publicKey);
            result = cipher.doFinal(result);
            System.out.println("私钥加密、公钥解密——解密：" + new String(result));


            // 3.公钥加密、私钥解密——加密
            x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
            keyFactory = KeyFactory.getInstance(RSA);
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            result = cipher.doFinal(STR.getBytes());
            System.out.println("公钥加密、私钥解密——加密：" + Base64.encodeBase64String(result));

            // 4.公钥加密、私钥解密——解密
            pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance(RSA);
            privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            result = cipher.doFinal(result);
            System.out.println("公钥加密、私钥解密——解密：" + new String(result));
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
    private static KeyPair generateSenderPublicKey() throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(RSA);
        senderKeyPairGenerator.initialize(512);
        return senderKeyPairGenerator.generateKeyPair();
    }
}
