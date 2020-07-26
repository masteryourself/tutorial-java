package pers.masteryourself.tutorial.secure.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.io.FileUtils;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * <p>description : RsaDemo
 * <p>公开密钥和私有密钥是一对
 * 如果用公开密钥对数据进行加密，只有用对应的私有密钥才能解密
 * 如果用私有密钥对数据进行加密，只有用对应的公开密钥才能解密
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/7/26 12:59
 */
public class RsaDemo {

    /**
     * 算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 公钥保存路径
     */
    private static final String PUBLIC_PATH = "a.pub";

    /**
     * 私钥保存路径
     */
    private static final String PRIVATE_PATH = "a.pri";

    /**
     * 公钥
     */
    private static PublicKey publicKey;

    /**
     * 私钥
     */
    private static PrivateKey privateKey;

    public static void main(String[] args) throws Exception {
        // 生成密钥对并保存在本地文件中
        generateKeyToFile();
        privateKey = getPrivateKey();
        publicKey = getPublicKey();
        // 明文
        String original = "今天中午吃啥呀";
        System.out.println("原文内容：" + original);
        System.out.println("-------------------------------------------私钥加密，公钥加密-------------------------------------------");
        // 私钥加密，公钥加密
        privateKeyEncrypt2publicKeyDecrypt(original);
        System.out.println("-------------------------------------------公钥加密，私钥解密-------------------------------------------");
        // 公钥加密，私钥解密
        publicKeyEncrypt2privateKeyDecrypt(original);
    }

    private static void generateKeyToFile() throws Exception {
        // 获取密钥对生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
        // 获取密钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        // 获取公钥
        PublicKey publicKey = keyPair.getPublic();
        // 获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        // 获取byte数组
        byte[] publicKeyEncoded = publicKey.getEncoded();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        // 进行Base64编码
        String publicKeyString = Base64.encode(publicKeyEncoded);
        String privateKeyString = Base64.encode(privateKeyEncoded);
        // 保存文件
        FileUtils.writeStringToFile(new File(PUBLIC_PATH), publicKeyString, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(new File(PRIVATE_PATH), privateKeyString, StandardCharsets.UTF_8);
    }

    /**
     * 从文件中读取私钥
     *
     * @return 私钥
     * @throws Exception 异常
     */
    public static PrivateKey getPrivateKey() throws Exception {
        // 将文件内容转为字符串
        String privateKeyString = FileUtils.readFileToString(new File(PRIVATE_PATH), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        // 构建密钥规范 进行Base64解码
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyString));
        // 生成私钥
        return keyFactory.generatePrivate(spec);
    }

    /**
     * 从文件中读取公钥
     *
     * @return 私钥
     * @throws Exception 异常
     */
    public static PublicKey getPublicKey() throws Exception {
        // 将文件内容转为字符串
        String publicKeyString = FileUtils.readFileToString(new File(PUBLIC_PATH), Charset.defaultCharset());
        // 获取密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        // 构建密钥规范 进行Base64解码
        X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.decode(publicKeyString));
        // 生成公钥
        return keyFactory.generatePublic(spec);
    }

    /**
     * 私钥加密，公钥加密
     *
     * @param original 原文
     * @throws Exception 异常
     */
    private static void privateKeyEncrypt2publicKeyDecrypt(String original) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptBytes = cipher.doFinal(original.getBytes());
        System.out.println("加密后内容：" + Base64.encode(encryptBytes));
        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println("解密后内容：" + new String(decryptBytes));
    }

    /**
     * 公钥加密，私钥加密
     *
     * @param original
     * @throws Exception
     */
    private static void publicKeyEncrypt2privateKeyDecrypt(String original) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 私钥加密
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptBytes = cipher.doFinal(original.getBytes());
        System.out.println("加密后内容：" + Base64.encode(encryptBytes));
        // 公钥解密
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        System.out.println("解密后内容：" + new String(decryptBytes));
    }

}
