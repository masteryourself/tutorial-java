package pers.masteryourself.tutorial.secure.rsa;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * <p>description : SignDemo
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/7/26 15:36
 */
public class SignDemo {

    /**
     * 算法
     */
    private static final String ALGORITHM = "sha256withrsa";

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
        publicKey = RsaDemo.getPublicKey();
        privateKey = RsaDemo.getPrivateKey();
        // 明文
        String original = "这是偷偷发给你的信息";
        System.out.println("原文内容：" + original);
        String signatureData = getSignature(original);
        System.out.println("签名后数据：" + signatureData);
        verifySignature(original, signatureData);
    }


    private static String getSignature(String original) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(ALGORITHM);
        // 初始化签名
        signature.initSign(privateKey);
        // 传入原文
        signature.update(original.getBytes());
        // 开始签名
        byte[] sign = signature.sign();
        // 对签名数据进行Base64编码
        return Base64.encode(sign);
    }

    private static void verifySignature(String original, String signatureData) throws Exception {
        // 获取签名对象
        Signature signature = Signature.getInstance(ALGORITHM);
        // 初始化签名
        signature.initVerify(publicKey);
        // 传入原文
        signature.update(original.getBytes());
        // 校验数据
        System.out.println("验证签名是否正确：" + signature.verify(Base64.decode(signatureData)));
    }

}
