package org.litespring.test.v5;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJAfterThrowingAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.framework.ReflectiveMethodInvocation;
import org.litespring.service.v5.GoodService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public class ReflectiveMethodInvocationTest {

    private AspectJBeforeAdvice         beforeAdvice;
    private AspectJAfterReturningAdvice returningAdvice;
    private AspectJAfterThrowingAdvice  throwingAdvice;
    private TransactionManager          tx;
    private GoodService                 targetObject = null;

    @Before
    public void setUp() throws NoSuchMethodException {
        tx = new TransactionManager();
        targetObject = new GoodService();
        beforeAdvice = new AspectJBeforeAdvice(TransactionManager.class.getDeclaredMethod("start"), tx, null);
        returningAdvice = new AspectJAfterReturningAdvice(TransactionManager.class.getDeclaredMethod("commit"), tx, null);
        throwingAdvice = new AspectJAfterThrowingAdvice(TransactionManager.class.getDeclaredMethod("rollback"), tx, null);
    }

    @Test
    public void testMethodInvocation() throws Throwable {
        Method targetMethod = GoodService.class.getMethod("placeOrder");
        List<MethodInterceptor> methodInterceptors = new ArrayList<>();
        methodInterceptors.add(beforeAdvice);
        methodInterceptors.add(returningAdvice);
        ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(targetObject, targetMethod, new Object[0], methodInterceptors);
        invocation.proceed();
        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start transaction", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit transaction", msgs.get(2));
    }

    @Test
    public void testThrowingAdvice() throws NoSuchMethodException {

        Method targetMethod = GoodService.class.getMethod("placeOrderWithException");
        List<MethodInterceptor> methodInterceptors = new ArrayList<>();
        methodInterceptors.add(throwingAdvice);
        methodInterceptors.add(beforeAdvice);
        ReflectiveMethodInvocation invocation = new ReflectiveMethodInvocation(targetObject, targetMethod, new Object[0], methodInterceptors);
        try {
            invocation.proceed();
        } catch (Throwable t) {
            List<String> msgs = MessageTracker.getMsgs();
            Assert.assertEquals(2, msgs.size());
            Assert.assertEquals("start transaction", msgs.get(0));
            Assert.assertEquals("rollback transaction", msgs.get(1));
        }
    }
}
