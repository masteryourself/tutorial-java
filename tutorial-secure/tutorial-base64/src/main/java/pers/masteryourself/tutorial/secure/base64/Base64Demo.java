package pers.masteryourself.tutorial.secure.base64;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * <p>description : Base64Demo
 * <p>Base64 是网络上最常见的用于传输 8Bit 字节码的可读性编码算法之一, 可读性编码算法不是为了保护数据的安全性，而是为了可读性
 * 所谓 Base64，即是说在编码过程中使用了 64 种字符：大写A到Z、小写a到z、数字0到9、+ 和 /
 * base64 是 3个字节为一组，一个字节 8位，一共 就是24位 ，然后，把3个字节转成4组，每组6位，3 * 8 = 4 * 6，
 * 每组6位，缺少的2位，会在高位进行补0 ，这样做的好处在于 ，base取的是后面6位，去掉高2位 ，
 * 那么base64的取值就可以控制在0-63位了，所以就叫base64，111111(BIN) = 63(DEC)
 * 因为base64是三个字节一组 ，如果当我们的位数不够的时候，会使用等号来补齐
 *
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/7/26 11:09
 */
public class Base64Demo {

    /**
     * Base64 编码表
     * 0 A 16 Q 32 g 48 w
     * 1 B 17 R 33 h 49 x
     * 2 C 18 S 34 i 50 y
     * 3 D 19 T 35 j 51 z
     * 4 E 20 U 36 k 52 0
     * 5 F 21 V 37 l 53 1
     * 6 G 22 W 38 m 54 2
     * 7 H 23 X 39 n 55 3
     * 8 I 24 Y 40 o 56 4
     * 9 J 25 Z 41 p 57 5
     * 10 K 26 a 42 q 58 6
     * 11 L 27 b 43 r 59 7
     * 12 M 28 c 44 s 60 8
     * 13 N 29 d 45 t 61 9
     * 14 O 30 e 46 u 62 +
     * 15 P 31 f 47 v 63 /
     */

    public static void main(String[] args) throws Exception {
        // 1个字节, 需要补2个 =
        // a -> ASCII 编码 -> 97 -> 二进制编码 -> 01100001 -> 拆分成 6 位, 不足补0 -> 011000 010000
        // -> 十进制 -> 24 16 -> Base64 编码 -> YQ==
        base64Test("a");
        // 2个字节, 需要补1个 =
        // ab -> ASCII 编码 -> 97 98 -> 二进制编码 -> 01100001 01100010 -> 拆分成 6 位, 不足补0 -> 011000 010110 001000
        // -> 十进制 -> 24 22 8 -> Base64 编码 -> YWI=
        base64Test("ab");
        // 3个字节
        // abc -> ASCII 编码 -> 97 98 99 -> 二进制编码 -> 01100001 01100010 01100011 -> 拆分成 6 位, 不足补0 -> 011000 010110 001001 100011
        // -> 十进制 -> 24 22 9 35 -> Base64 编码 -> YWJj
        base64Test("abc");
    }

    private static void base64Test(String str) {
        System.out.println(Base64.encode(str.getBytes()));
    }

}
