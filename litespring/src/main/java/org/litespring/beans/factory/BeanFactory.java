package org.litespring.beans.factory;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午11:18
 **/
public interface BeanFactory {

    Object getBean(String beanId);

    Class<?> getType(String targetBeanName);
}
