package com.zhy.util;

/**
 * @author zhy
 * 断言对象是否为空
 */
public class Assert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
