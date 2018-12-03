package com.chen.encryption.util;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.DHParameterSpec;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 基于 ElGamal 算法的非对称加密工具类
 *
 * @Author LeifChen
 * @Date 2018-11-30
 */
public class ElGamalUtils {

    static final String ELGAMAL = "ElGamal";
    static final String STR = "Hello LeifChen";

    public static void bcElGamal() {
        Security.addProvider(new BouncyCastleProvider());

        try {
            AlgorithmParameterGenerator algorithmParameterGenerator = AlgorithmParameterGenerator.getInstance(ELGAMAL);
            System.out.println(algorithmParameterGenerator.getProvider());
            algorithmParameterGenerator.init(256);
            AlgorithmParameters algorithmParameters = algorithmParameterGenerator.generateParameters();
            DHParameterSpec dhParameterSpec = algorithmParameters.getParameterSpec(DHParameterSpec.class);

            // 构建密钥对
            KeyPair keyPair = generateSenderPublicKey(dhParameterSpec);
            PublicKey elGamalPublicKey = keyPair.getPublic();
            PrivateKey elGamalPrivateKey = keyPair.getPrivate();

            // 公钥加密、私钥解密——加密
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(elGamalPublicKey.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance(ELGAMAL);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] result = cipher.doFinal(STR.getBytes());
            System.out.println("公钥加密、私钥解密——加密：" + Base64.encodeBase64String(result));

            // 公钥加密、私钥解密——解密
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(elGamalPrivateKey.getEncoded());
            keyFactory = KeyFactory.getInstance(ELGAMAL);
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            cipher = Cipher.getInstance(keyFactory.getAlgorithm());
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
    private static KeyPair generateSenderPublicKey(DHParameterSpec dhParameterSpec) throws Exception {
        KeyPairGenerator senderKeyPairGenerator = KeyPairGenerator.getInstance(ELGAMAL);
        senderKeyPairGenerator.initialize(dhParameterSpec, new SecureRandom());
        return senderKeyPairGenerator.generateKeyPair();
    }
}
