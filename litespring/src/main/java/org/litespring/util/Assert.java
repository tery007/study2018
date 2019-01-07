package org.litespring.util;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/6 下午2:05
 **/
public abstract class Assert {
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
