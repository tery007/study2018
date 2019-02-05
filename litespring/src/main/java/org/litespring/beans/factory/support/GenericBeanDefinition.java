package org.litespring.beans.factory.support;

import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinition;

import java.util.ArrayList;
import java.util.List;

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

    private List<PropertyValue> propertyValues = new ArrayList<>();

    private ConstructorArgument argument = new ConstructorArgument();


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

    @Override
    public List<PropertyValue> getPropertyValues(){
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.argument;
    }

    @Override
    public boolean hasConstructorArgumentValues() {
        return !this.argument.isEmpty();
    }

    @Override
    public String getID() {
        return this.beanId;
    }

}
