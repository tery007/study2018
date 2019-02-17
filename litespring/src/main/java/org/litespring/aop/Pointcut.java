package org.litespring.aop;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/17
 **/
public interface Pointcut {

    MethodMatcher getMethodMatcher();

    String getExpression();
}
