package org.litespring.beans.factory.config;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/29 上午11:47
 **/
public class RuntimeBeanReference {

    private final String beanName;

    public RuntimeBeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return this.beanName;
    }
}
