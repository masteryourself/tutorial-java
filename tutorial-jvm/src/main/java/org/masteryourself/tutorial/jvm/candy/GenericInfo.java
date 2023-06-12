package org.masteryourself.tutorial.jvm.candy;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>description : 获取泛型信息
 *
 * <p>blog : https://www.yuque.com/masteryourself
 *
 * @author : masteryourself
 * @version : 1.0.0
 * @date : 2022/5/4 3:51 PM
 */
public class GenericInfo {

    public Set<Integer> test(List<String> list, Map<Integer, Object> map) {
        return null;
    }

    /**
     * 输出结果:
     * 原始类型 - interface java.util.List
     * 泛型参数[0] - class java.lang.String
     * 原始类型 - interface java.util.Map
     * 泛型参数[0] - class java.lang.Integer
     * 泛型参数[1] - class java.lang.Object
     */
    public static void main(String[] args) throws Exception {
        Method test = GenericInfo.class.getMethod("test", List.class, Map.class);
        Type[] types = test.getGenericParameterTypes();
        for (Type type : types) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                System.out.println("原始类型 - " + parameterizedType.getRawType());
                Type[] arguments = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < arguments.length; i++) {
                    System.out.printf("泛型参数[%d] - %s\n", i, arguments[i]);
                }
            }
        }
    }

}
