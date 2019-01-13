package org.litespring.beans;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/12 下午12:41
 **/
public interface TypeConverter {

    <T> T convertIfNeccessary(Object value, Class<T> targetClass);

}
