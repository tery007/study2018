package org.litespring.beans.factory.support;

import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.PropertyValue;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.util.Assert;

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
    private Class<?> beanClass;
    private String scope;
    private boolean singleton = true;

    private boolean prototype = false;

    private List<PropertyValue> propertyValues = new ArrayList<>();

    private ConstructorArgument argument = new ConstructorArgument();

    public GenericBeanDefinition() {

    }

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

    public void setBeanClassName(String className) {
        this.className = className;
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

    /**
     * 调用getBeanClass()之前必须调用resolveBeanClass()方法初始化
     * @return
     */
    @Override
    public Class<?> getBeanClass() {
        if (this.beanClass == null) {
            throw new IllegalStateException(
                    "Bean class name [" + this.getBeanClassName() + "] has not been resolved into an actual Class");
        }
        return this.beanClass;
    }

    @Override
    public Class<?> resolveBeanClass(ClassLoader classLoader) throws ClassNotFoundException {
        String className = getBeanClassName();
        Assert.notNull(className, "className is null");
        Class<?> resolvedClass = classLoader.loadClass(className);
        this.beanClass = resolvedClass;
        return resolvedClass;

    }

    @Override
    public boolean hasBeanClass() {
        return this.beanClass != null;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

}
