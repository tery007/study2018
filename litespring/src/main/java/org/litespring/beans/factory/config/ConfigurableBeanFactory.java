package org.litespring.beans.factory.config;

import java.util.List;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午5:44
 **/
public interface ConfigurableBeanFactory extends AutowireCapableBeanFactory {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();

    void addBeanPostProcessor(BeanPostProcessor postProcessor);
    List<BeanPostProcessor> getBeanPostProcessors();
}
