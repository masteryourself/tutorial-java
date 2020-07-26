package pers.masteryourself.tutorial.secure.des;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * <p>description : AesDemo
 * <p>对称加密的一种, 比 DES 更高级、安全, 常配合 Base64 一起使用
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/7/26 11:45
 */
public class AesDemo {

    public static void main(String[] args) throws Exception {
        // 明文
        String original = "今天中午吃啥呀";
        System.out.println("原文内容：" + original);
        // 加密秘钥，必须是16位
        String key = "2244668811335577";
        String encryptStr = encrypt(original, key);
        System.out.println("加密后并且经过 Base64 编码后内容：" + encryptStr);
        String decryptStr = decrypt(encryptStr, key);
        System.out.println("解密后内容：" + decryptStr);
    }

    /**
     * 加密
     *
     * @param original 原文
     * @param key      加密秘钥
     * @throws Exception 异常
     */
    private static String encrypt(String original, String key) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance("AES");
        // 创建加密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        // 加密初始化
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        // 调用加密方法
        byte[] encryptBytes = cipher.doFinal(original.getBytes());
        // 这里出现乱码是因为在 Ascii 码表里没有对应的内容
        System.out.println("加密后内容：" + new String(encryptBytes));
        // 使用 Base64 编码
        return Base64.encode(encryptBytes);
    }

    /**
     * 解密
     *
     * @param encryptStr 原文
     * @param key        解密秘钥
     * @throws Exception 异常
     */
    private static String decrypt(String encryptStr, String key) throws Exception {
        // 创建加密对象
        Cipher cipher = Cipher.getInstance("AES");
        // 创建加密规则
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        // 加密初始化
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        // 调用加密方法, 使用 Base64 解码
        byte[] decryptBytes = cipher.doFinal(Base64.decode(encryptStr));
        return new String(decryptBytes);
    }

}
