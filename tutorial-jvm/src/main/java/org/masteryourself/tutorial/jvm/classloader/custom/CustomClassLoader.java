package org.masteryourself.tutorial.jvm.classloader.custom;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <p>description : CustomClassLoader
 *
 * <p>blog : https://www.yuque.com/ruanrenzhao/
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/5 11:36 AM
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String path = "file/tutorial-jvm/" + name + ".class";
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Files.copy(Paths.get(path), os);
            // 得到字节数组
            byte[] bytes = os.toByteArray();
            // 将字节数组转成 class 对象
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            throw new ClassNotFoundException(name + "未找到");
        }
    }

}
