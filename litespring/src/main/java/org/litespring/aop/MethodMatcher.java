package org.litespring.aop;

import java.lang.reflect.Method;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/17
 **/
public interface MethodMatcher {

    boolean matches(Method method);
}
