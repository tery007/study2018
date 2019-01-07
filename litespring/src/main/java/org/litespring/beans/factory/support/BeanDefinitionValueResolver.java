package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.RuntimeBeanReference;
import org.litespring.beans.factory.config.TypedStringValue;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2019/1/1 下午3:28
 **/
public class BeanDefinitionValueResolver {

    private DefaultBeanFactory factory;

    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            return factory.getBean(reference.getBeanName());
        }
        if (value instanceof TypedStringValue) {
            return ((TypedStringValue) value).getValue();
        }
        throw new RuntimeException("the value " + value + " has not implemented");
    }
}
