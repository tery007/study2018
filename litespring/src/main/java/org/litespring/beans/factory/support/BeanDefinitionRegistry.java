package org.litespring.beans.factory.support;

import org.litespring.beans.factory.BeanDefinition;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午4:49
 **/
public interface BeanDefinitionRegistry {

    /**
     *  注册bean
     * @param beanId
     * @param clazz
     */
    void registerBeanDefinition(String beanId, BeanDefinition clazz);

    /**
     * 根据beanId获取BeanDefinition
     * @param beanId
     * @return
     */
    BeanDefinition getBeanDefinition(String beanId);



}
