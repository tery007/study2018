package org.litespring.aop.framework;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/3/14
 **/
public interface AopProxyFactory {

    Object getProxy();

    Object getProxy(ClassLoader classLoader);
}
