package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午5:44
 **/
public interface ConfigurableBeanFactory extends BeanFactory {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}