package pers.masteryourself.tutorial.jvm.gc;


/**
 * <p>description : CPUTest
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 20:09
 */
public class CPUTest {

    public static void main(String[] args) {
        while (true) {
            int begin = new java.util.Random().nextInt(545775512);
            for (int i = 0; i < 50; i++) {
                begin = begin + new java.util.Random().nextInt(545775512);
            }
            System.out.println(begin);
        }
    }

}
