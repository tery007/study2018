package org.litespring.beans.factory.config;

import org.litespring.beans.factory.BeanFactory;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public interface AutowiredCapableBeanFactory extends BeanFactory {

    Object resolveDependency(DependencyDescriptor descriptor);
}
