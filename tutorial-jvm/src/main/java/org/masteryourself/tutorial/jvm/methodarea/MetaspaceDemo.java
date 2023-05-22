package org.masteryourself.tutorial.jvm.methodarea;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * <p>description : 元空间内存溢出案例
 *
 * <p>blog : https://www.yuque.com/masteryoursef
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/4/30 3:57 PM
 */
public class MetaspaceDemo extends ClassLoader {

    /**
     * 添加 VM 参数 -XX:MaxMetaspaceSize=32m
     */
    public static void main(String[] args) {
        try {
            MetaspaceDemo metaspace = new MetaspaceDemo();
            for (int i = 0; i < 100000; i++) {
                // 生成类的字节码文件
                ClassWriter cw = new ClassWriter(0);
                cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                byte[] code = cw.toByteArray();
                // 执行类的加载
                metaspace.defineClass("Class" + i, code, 0, code.length);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

}
