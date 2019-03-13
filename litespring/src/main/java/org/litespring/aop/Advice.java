package org.litespring.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/24
 **/
public interface Advice extends MethodInterceptor {

    Pointcut getPointcut();

}
