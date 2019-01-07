package org.litespring.beans.factory;

import org.litespring.beans.PropertyValue;

import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/26 上午10:17
 **/
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";
    String getScope();
    void setScope(String scope);
    String getBeanClassName();

    boolean isSingleton();

    boolean isPrototype();

    List<PropertyValue> getPropertyValues();
}
