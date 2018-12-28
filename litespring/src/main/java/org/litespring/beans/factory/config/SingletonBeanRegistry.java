package org.litespring.beans.factory.config;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/28 上午11:02
 **/
public interface SingletonBeanRegistry {

    void singletonBeanRegister(String beanName, Object beanInstance);

    Object getSingletonBean(String beanName);
}
