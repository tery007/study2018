package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public class AspectJAfterThrowingAdvice extends AbstracAspectJAdvice {

    public AspectJAfterThrowingAdvice(Method adviceMethod, Object adviceObject, AspectJExpressionPointcut pointcut) {
        super(adviceMethod, adviceObject, pointcut);
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Throwable throwable) {
            invokeAdviceMethod();
            throw throwable;
        }
    }
}
