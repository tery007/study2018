package org.litespring.beans.factory.support;

import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.BeanFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午11:31
 **/
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    public static final Map<String, BeanDefinition> beanMap = new ConcurrentHashMap(1024);

    public DefaultBeanFactory() {

    }


    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        beanMap.put(beanId, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {
        BeanDefinition definition = getBeanDefinition(beanId);
        String beanClassName = definition.getBeanClassName(beanId);
        try {
            Class<?> clazz = Class.forName(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for "+ beanClassName +" failed",e);
        }
    }
}
