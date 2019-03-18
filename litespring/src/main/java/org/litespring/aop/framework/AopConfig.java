package org.litespring.aop.framework;

import org.litespring.aop.Advice;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/14
 **/
public interface AopConfig {

    Class<?> getTargetClass();

    Object getTargetObject();

    void setTargetObject(Object targetObject);

    List<Advice> getAdvices();

    void addAdvice(Advice advice);

    List<Advice> getAdvices(Method method/*,Class<?> targetClass*/);



}
