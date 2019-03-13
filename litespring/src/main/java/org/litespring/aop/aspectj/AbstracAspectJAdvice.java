package org.litespring.aop.aspectj;

import org.litespring.aop.Advice;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/12
 **/
public abstract class AbstracAspectJAdvice implements Advice {

    protected Method   adviceMethod;
    protected Object   adviceObject;
    protected AspectJExpressionPointcut pointcut;

    public AbstracAspectJAdvice(Method adviceMethod, Object adviceObject, AspectJExpressionPointcut pointcut) {
        this.adviceMethod = adviceMethod;
        this.adviceObject = adviceObject;
        this.pointcut = pointcut;
    }

    @Override
    public AspectJExpressionPointcut getPointcut() {
        return this.pointcut;
    }

    protected void invokeAdviceMethod() throws Throwable {
        adviceMethod.invoke(adviceObject);
    }
}
