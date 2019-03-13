package org.litespring.aop.aspectj;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public class AspectJAfterReturningAdvice extends AbstracAspectJAdvice {


    public AspectJAfterReturningAdvice(Method adviceMethod, Object adviceObject, AspectJExpressionPointcut pointcut) {
        super(adviceMethod, adviceObject, pointcut);
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        //例如：调用GoodService的placeOrder()方法
        Object o = mi.proceed();
        //例如：调用TransactionManager的commit()方法
        this.invokeAdviceMethod();
        return o;
    }
}
