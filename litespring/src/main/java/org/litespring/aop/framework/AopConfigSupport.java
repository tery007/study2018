package org.litespring.aop.framework;

import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/14
 **/
public class AopConfigSupport implements AopConfig {

    private Object targetObject;

    private List<Advice> advices = new ArrayList<>();

    public AopConfigSupport(){}

    @Override
    public Class<?> getTargetClass() {
        return targetObject.getClass();
    }

    @Override
    public Object getTargetObject() {
        return this.targetObject;
    }

    @Override
    public void setTargetObject(Object targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public List<Advice> getAdvices() {
        return this.advices;
    }

    @Override
    public void addAdvice(Advice advice) {
        this.advices.add(advice);
    }

    @Override
    public List<Advice> getAdvices(Method method) {
        List<Advice> result = new ArrayList<>();
        for (Advice advice : this.advices) {
            Pointcut pointcut = advice.getPointcut();
            if (pointcut.getMethodMatcher().matches(method)) {
                result.add(advice);
            }
        }
        return result;
    }
}
