package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;
import org.litespring.aop.Advice;
import org.litespring.aop.Pointcut;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public class AspectJBeforeAdvice implements Advice {

    private Method adviceMethod;
    private Object adviceObject;
    private Pointcut pointcut;

    public AspectJBeforeAdvice(Method adviceMethod, Object adviceObject, Pointcut pointcut) {
        this.adviceMethod = adviceMethod;
        this.adviceObject = adviceObject;
        this.pointcut = pointcut;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //例如：调用TransactionManager的start()方法
        adviceMethod.invoke(adviceObject);
        //再调用MethodInvocation，进行其他拦截器方法的调用
        Object o = mi.proceed();
        return o;
    }
}
