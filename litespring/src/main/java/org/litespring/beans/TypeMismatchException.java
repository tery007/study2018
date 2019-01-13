package org.litespring.beans;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/12 下午12:34
 **/
public class TypeMismatchException extends BeansException {


    private Object value;
    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Failed to convert value :" + value + "to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }
}
