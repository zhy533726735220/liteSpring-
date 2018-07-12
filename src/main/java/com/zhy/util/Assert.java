package com.zhy.util;

/**
 * @author zhy
 * 断言对象是否为空
 */
public class Assert {
    /**
     * 判断对象是否为空
     * @param object
     * @param message
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
