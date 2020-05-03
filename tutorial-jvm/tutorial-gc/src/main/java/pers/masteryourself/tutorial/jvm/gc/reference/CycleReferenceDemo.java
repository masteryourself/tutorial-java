package pers.masteryourself.tutorial.jvm.gc.reference;

/**
 * <p>description : CycleReference
 *
 * <p>blog : https://blog.csdn.net/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2020/5/3 2:36
 */
public class CycleReferenceDemo {

    private Object instance;

    public static void main(String[] args) {
        CycleReferenceDemo cycleReference1 = new CycleReferenceDemo();
        CycleReferenceDemo cycleReference2 = new CycleReferenceDemo();
        cycleReference1.instance = cycleReference2;
        cycleReference2.instance = cycleReference1;
        cycleReference1 = null;
        cycleReference2 = null;
        System.gc();
    }

}
