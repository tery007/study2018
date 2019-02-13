package org.litespring.beans.factory.config;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午5:44
 **/
public interface ConfigurableBeanFactory extends AutowiredCapableBeanFactory {

    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
