package org.litespring.beans.factory.support;

import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午11:31
 **/
public class DefaultBeanFactory implements BeanFactory {

    public DefaultBeanFactory(String fileName) {

    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return null;
    }

    @Override
    public Object getBean(String beanId) {
        return null;
    }
}
