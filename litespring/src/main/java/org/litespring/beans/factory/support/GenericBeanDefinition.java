package org.litespring.beans.factory.support;

import org.litespring.beans.factory.BeanDefinition;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午5:15
 **/
public class GenericBeanDefinition implements BeanDefinition {

    private String beanId;
    private String className;

    private String scope;
    private boolean singleton = true;

    private boolean prototype = false;



    public GenericBeanDefinition(String beanId,String className) {
        this.beanId = beanId;
        this.className = className;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public String getBeanClassName() {
        return this.className;
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

}
