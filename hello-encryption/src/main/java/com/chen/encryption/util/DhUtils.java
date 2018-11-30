package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Objects;

/**
 * 基于 DH 算法的非对称加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class DhUtils {

    static final String DH = "DH";
    static final String DES = "DES";
    static final String STR = "Hello LeifChen";

    public static void jdkDH() {
        try {
            // 1.发送方构建公钥私钥
            KeyPair senderKeyPair = generateSenderPublicKey();
            // 2.发送方发布公钥
            byte[] senderPublicKeyBytes = senderKeyPair.getPublic().getEncoded();
            // 3.接收方构建公钥私钥->接收方通过发送方公钥构建公钥私钥
            KeyPair receiverKeyPair = generateReceiverPublicKey(senderPublicKeyBytes);
            // 4.接收方发布公钥
            byte[] receiverPublicKeyBytes = receiverKeyPair.getPublic().getEncoded();
            // 5.接收方构建对称加密秘钥->依据发送方公钥和接收方公钥私钥构建
            SecretKey receiverDESKey = generateSecretKey(receiverKeyPair, senderPublicKeyBytes);
            // 6.发送方构建对称加密秘钥->依据接收方公钥和发送方公钥私钥构建
            SecretKey senderDESKey = generateSecretKey(senderKeyPair, receiverPublicKeyBytes);

            if (Objects.equals(receiverDESKey, senderDESKey)) {
                System.out.println("双方密钥相同");
            }

            // 7.发送方加密
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(Cipher.ENCRYPT_MODE, senderDESKey);
            byte[] result = cipher.doFinal(STR.getBytes());
            System.out.println("JDK DH Encrypt: " + Base64.encodeBase64String(result));
            // 8.接收方解密
            cipher.init(Cipher.DECRYPT_MODE, receiverDESKey);
            result = cipher.doFinal(result);
            System.out.println("JDK DH Decrypt: " + new String(result));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 发送方构建密钥
     *
     * @return 构建完的公钥私钥
     * @throws NoSuchAlgorithmException
     */
    private static KeyPair generateSenderPublicKey() throws NoSuchAlgorithmException {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(DH);
        senderKeyPairGenerator.initialize(512);
        return senderKeyPairGenerator.generateKeyPair();
    }

    /**
     * 依据发送方公钥构建接收方公钥私钥
     *
     * @param senderPublicKey 发送方公钥
     * @return 接收方公钥私钥
     * @throws Exception
     */
    private static KeyPair generateReceiverPublicKey(byte[] senderPublicKey) throws Exception {
        KeyFactory receiverKeyFactory = KeyFactory.getInstance(DH);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(senderPublicKey);
        PublicKey receiverPublicKey = receiverKeyFactory.generatePublic(x509EncodedKeySpec);
        DHParameterSpec dhParameterSpec = ((DHPublicKey) receiverPublicKey).getParams();
        KeyPairGenerator receiverKeyPairGenerator = KeyPairGenerator.getInstance(DH);
        receiverKeyPairGenerator.initialize(dhParameterSpec);
        return receiverKeyPairGenerator.generateKeyPair();
    }

    /**
     * 使用自己的公钥私钥与对方的公钥构建 对称密钥
     *
     * @param keyPair        自己的公钥私钥
     * @param publicKeyBytes 对方的公钥
     * @return 本地对称加密密钥
     * @throws Exception
     */
    private static SecretKey generateSecretKey(KeyPair keyPair, byte[] publicKeyBytes) throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance(DH);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(publicKeyBytes);
        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        KeyAgreement keyAgreement = KeyAgreement.getInstance(DH);
        keyAgreement.init(keyPair.getPrivate());
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret(DES);
    }
}
