package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.aop.aspectj.AspectJAfterReturningAdvice;
import org.litespring.aop.aspectj.AspectJBeforeAdvice;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.aop.framework.AopConfig;
import org.litespring.aop.framework.AopConfigSupport;
import org.litespring.aop.framework.CglibProxyFactory;
import org.litespring.service.v5.GoodService;
import org.litespring.tx.TransactionManager;
import org.litespring.util.MessageTracker;

import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/13
 **/
public class CglibAopProxyTest {

    private static AspectJBeforeAdvice         beforeAdvice = null;
    private static AspectJAfterReturningAdvice afterAdvice  = null;
    private static AspectJExpressionPointcut   pc           = null;

    private TransactionManager tx;

    @Before
    public  void setUp() throws Exception{


        tx = new TransactionManager();
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        pc = new AspectJExpressionPointcut();
        pc.setExpression(expression);

        beforeAdvice = new AspectJBeforeAdvice(
                TransactionManager.class.getMethod("start"),
                tx,
                pc);

        afterAdvice = new AspectJAfterReturningAdvice(
                TransactionManager.class.getMethod("commit"),
                tx,
                pc);

    }

    @Test
    public void testCglibAopProxy() {
        AopConfig config = new AopConfigSupport();
        config.addAdvice(beforeAdvice);
        config.addAdvice(afterAdvice);
        config.setTargetObject(new GoodService());

        CglibProxyFactory proxyFactory = new CglibProxyFactory(config);
        GoodService proxy = (GoodService) proxyFactory.getProxy();
        proxy.placeOrder();

        List<String> msgs = MessageTracker.getMsgs();
        Assert.assertEquals(3, msgs.size());
        Assert.assertEquals("start transaction", msgs.get(0));
        Assert.assertEquals("place order", msgs.get(1));
        Assert.assertEquals("commit transaction", msgs.get(2));

        proxy.toString();
    }
}
