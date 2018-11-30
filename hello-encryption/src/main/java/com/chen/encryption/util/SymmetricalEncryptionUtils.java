package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.*;
import java.security.Key;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 对称加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class SymmetricalEncryptionUtils {

    /**
     * 使用 JDK DES 加密
     *
     * @param str 明文
     * @return DES 密文
     */
    public static String jdkDES(String str) {
        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("JDK DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 BC DES 加密
     *
     * @param str 明文
     * @return DES 密文
     */
    public static String bcDES(String str) {
        Security.addProvider(new BouncyCastleProvider());

        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            DESKeySpec desKeySpec = new DESKeySpec(keyBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("BC DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 JDK 3DES 加密
     *
     * @param str 明文
     * @return 3DES 密文
     */
    public static String jdk3DES(String str) {
        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(keyBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("JDK 3DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 BC 3DES 加密
     *
     * @param str 明文
     * @return 3DES 密文
     */
    public static String bc3DES(String str) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede", "BC");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(keyBytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desKeySpec);

            // 加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("BC 3DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 JDK DES 加密
     *
     * @param str 明文
     * @return DES 密文
     */
    public static String jdkAES(String str) {
        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            Key convertSecretKey = new SecretKeySpec(keyBytes, "DES");

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("JDK DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 BC DES 加密
     *
     * @param str 明文
     * @return DES 密文
     */
    public static String bcAES(String str) {
        Security.addProvider(new BouncyCastleProvider());
        try {
            // 生成 KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES", "BC");
            System.out.println(keyGenerator.getProvider());
            keyGenerator.init(new SecureRandom());
            Key secretKey = keyGenerator.generateKey();
            byte[] keyBytes = secretKey.getEncoded();

            // KEY 转换
            Key convertSecretKey = new SecretKeySpec(keyBytes, "DES");

            // 加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            System.out.println("BC DES Decrypt: " + new String(cipher.doFinal(result)));

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 JDK PBE 加密
     *
     * @param str 明文
     * @return PBE 密文
     */
    public static String jdkPBE(String str) {
        // 初始化盐
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        // 口令与密钥
        String password = "test";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            System.out.println(factory.getProvider());
            Key key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            System.out.println("JDK PBE Decrypt: " + new String(cipher.doFinal(result)));

            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 使用 BC PBE 加密
     *
     * @param str 明文
     * @return PBE 密文
     */
    public static String bcPBE(String str) {
        // 初始化盐
        SecureRandom random = new SecureRandom();
        byte[] salt = random.generateSeed(8);

        // 口令与密钥
        String password = "test";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());

        Security.addProvider(new BouncyCastleProvider());
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBEWITHMD5andDES","BC");
            System.out.println(factory.getProvider());
            Key key = factory.generateSecret(pbeKeySpec);

            // 加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt, 100);
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE, key, pbeParameterSpec);
            byte[] result = cipher.doFinal(str.getBytes());

            // 解密
            cipher.init(Cipher.DECRYPT_MODE, key, pbeParameterSpec);
            System.out.println("JDK PBE Decrypt: " + new String(cipher.doFinal(result)));

            return Base64.encodeBase64String(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
