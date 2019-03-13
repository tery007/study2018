package org.litespring.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/12
 **/
public class ReflectiveMethodInvocation implements MethodInvocation {

    protected final Method targetMethod;
    protected final Object targetObject;
    protected Object[] arguments;

    protected List<MethodInterceptor> interceptors;

    private int currentInterceptorIndex = -1;

    public ReflectiveMethodInvocation(
            Object target, Method method, Object[] arguments,
            List<MethodInterceptor> interceptors) {

        this.targetObject = target;
        this.targetMethod = method;
        this.arguments = arguments;
        this.interceptors = interceptors;
    }


    @Override
    public Method getMethod() {
        return this.targetMethod;
    }

    @Override
    public Object[] getArguments() {
        return (this.arguments != null ? this.arguments : new Object[0]);
    }

    @Override
    public Object proceed() throws Throwable {
        if (this.interceptors.size() - 1 == currentInterceptorIndex) {
            return invokeJoinPoint();
        }
        currentInterceptorIndex++;
        MethodInterceptor interceptor = interceptors.get(currentInterceptorIndex);
        return interceptor.invoke(this);
    }

    private Object invokeJoinPoint() throws Throwable {
        return this.targetMethod.invoke(targetObject, this.getArguments());
    }

    @Override
    public Object getThis() {
        return this.targetObject;
    }

    @Override
    public AccessibleObject getStaticPart() {
        return null;
    }
}
