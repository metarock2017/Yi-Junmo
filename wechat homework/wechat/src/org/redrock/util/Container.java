package org.redrock.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 容器类
 * 维护了一个 Map 实现对多个对象进行 饿汉式单例加载
 */
public class Container {
    /**
     * 容器
     */
    private static Map containers = new HashMap();

    /**
     * 从容器中获取类实例
     * 加锁
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T>T  getInstance(Class<T> clazz)  {
        if (containers.get(clazz.getName()) == null) {
            synchronized (Container.class) {
                if (containers.get(clazz.getName()) == null) {
                    containers.put(clazz.getName(), getClassInstance(clazz));
                }
            }
        }
        return (T) containers.get(clazz.getName());
    }

    /**
     * 向容器中放入实例
     * @param name
     * @param object
     */
    public static void addInstance(String name, Object object) {
        containers.put(name, object);
    }
    /**
     * 通过类获取类的实例
     * 反射 泛型
     * @param clazz
     * @param <T>
     * @return
     */
    private static <T>T  getClassInstance(Class<T> clazz)  {
        T instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
