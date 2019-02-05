package org.litespring.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午3:14
 **/
public class ClassUtils {

    /**
     * 包装类对应的基本类型
     */
    private final static Map<Class<?>, Class<?>> wapperToPrimitiveTypeMap = new HashMap<>();

    /**
     * 基本类型对应的包装类
     */
    private final static Map<Class<?>, Class<?>> primitiveTowapperTypeMap = new HashMap<>();

    static {
        wapperToPrimitiveTypeMap.put(Integer.class, int.class);
        wapperToPrimitiveTypeMap.put(Boolean.class, boolean.class);
        wapperToPrimitiveTypeMap.put(Byte.class, byte.class);
        wapperToPrimitiveTypeMap.put(Character.class, char.class);
        wapperToPrimitiveTypeMap.put(Double.class, double.class);
        wapperToPrimitiveTypeMap.put(Long.class, long.class);
        wapperToPrimitiveTypeMap.put(Short.class, short.class);
        wapperToPrimitiveTypeMap.put(Float.class, float.class);

        wapperToPrimitiveTypeMap.forEach((k,v)->{
            primitiveTowapperTypeMap.put(v, k);
        });
    }

    public static ClassLoader getDefaultClassLoader() {
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
            if (cl == null) {
                // getBeanClassLoader() returning null indicates the bootstrap ClassLoader
                try {
                    cl = ClassLoader.getSystemClassLoader();
                }
                catch (Throwable ex) {
                    // Cannot access system ClassLoader - oh well, maybe the caller can live with null...
                }
            }
        }
        return cl;
    }

    public static <T> boolean isAssignableValue(Class<T> type, Object value) {
        Assert.notNull(value, "value must not be null" + value);
        return value != null ? isAssignable(type, value.getClass()) : !type.isPrimitive();
    }

    /**
     * 1、isAssignableFrom：lhsType是rhsType的子类或者相同的类
     * 2、lhsType是基本类型（int、boolean...)，rhsType是包装类型，判断rhsType的基本类型是否与lhsType是否相同
     * 3、lhsType不是基本类型，rhs是基本类型，判断rhs对应的包装类是否与lhsType相同
     *
     * @param lhsType
     * @param rhsType
     * @return
     */
    public static boolean isAssignable(Class<?> lhsType, Class<?> rhsType) {
        Assert.notNull(lhsType, "left hand side type must be not null:" + lhsType);
        Assert.notNull(rhsType, "right hand side type must be not null:" + rhsType);
        if (lhsType.isAssignableFrom(rhsType)) {
            return true;
        }
        if (lhsType.isPrimitive()) {
            Class<?> primitiveClass = wapperToPrimitiveTypeMap.get(rhsType);
            if (primitiveClass != null && lhsType.equals(primitiveClass)) {
                return true;
            }
        } else {
            Class<?> wapperClass = primitiveTowapperTypeMap.get(rhsType);
            if (wapperClass != null && lhsType.equals(wapperClass)) {
                return true;
            }
        }
        return false;
    }
}
