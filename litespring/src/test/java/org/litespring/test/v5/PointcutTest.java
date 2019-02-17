package org.litespring.test.v5;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.aop.MethodMatcher;
import org.litespring.aop.aspectj.AspectJExpressionPointcut;
import org.litespring.service.v5.GoodService;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/16
 **/
public class PointcutTest {

    @Test
    public void testPointcut() throws NoSuchMethodException {
        String expression = "execution(* org.litespring.service.v5.*.placeOrder(..))";
        AspectJExpressionPointcut ap = new AspectJExpressionPointcut();
        ap.setExpression(expression);

        MethodMatcher mm = ap.getMethodMatcher();

        {
            Class<?> targetClass = GoodService.class;
            Method method1 = targetClass.getMethod("placeOrder");
            Assert.assertTrue(mm.matches(method1));
            Method method2 = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method2));

        }

        {
            Class<?> targetClass = org.litespring.service.v4.GoodService.class;
            Method method = targetClass.getMethod("getAccountDao");
            Assert.assertFalse(mm.matches(method));
        }
    }
}
