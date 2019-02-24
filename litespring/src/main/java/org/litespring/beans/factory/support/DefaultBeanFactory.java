package org.litespring.beans.factory.support;

import org.litespring.beans.PropertyValue;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.NoSuchBeanDefinitionException;
import org.litespring.beans.factory.config.BeanPostProcessor;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.config.DependencyDescriptor;
import org.litespring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.litespring.util.ClassUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wanglei
 * @description
 * @date Created on 2018/12/25 下午11:31
 **/
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanMap = new ConcurrentHashMap(1024);

    private ClassLoader beanClassLoader;

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<BeanPostProcessor>();

    @Override
    public void registerBeanDefinition(String beanId, BeanDefinition beanDefinition) {
        beanMap.put(beanId, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanMap.get(beanId);
    }

    @Override
    public Object getBean(String beanId) {

        BeanDefinition beanDefinition = getBeanDefinition(beanId);
        if (beanDefinition == null) {
            return null;
        }
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingletonBean(beanId);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.singletonBeanRegister(beanId, bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }


    private Object createBean(BeanDefinition beanDefinition) {
        Object bean = instantiateBean(beanDefinition);
        populateBean(bean, beanDefinition);
        return bean;
    }

    /**
     * 创建bean实例
     * @param beanDefinition
     * @return
     */
    private Object instantiateBean(BeanDefinition beanDefinition) {
        if (beanDefinition.hasConstructorArgumentValues()) {
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowiedConstructor(beanDefinition);
        }
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();
        try {
            Class<?> clazz = classLoader.loadClass(beanClassName);
            return clazz.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException("create bean for " + beanClassName + " failed", e);
        }
    }

    /**
     * 通过反射的方式将bean的属性设置到bean中
     *
     * @param bean
     * @param bd
     */
    private void populateBean(Object bean, BeanDefinition bd) {

        for(BeanPostProcessor processor : this.getBeanPostProcessors()){
            if(processor instanceof InstantiationAwareBeanPostProcessor){
                ((InstantiationAwareBeanPostProcessor)processor).postProcessPropertyValues(bean, bd.getID());
            }
        }

        List<PropertyValue> pvs = bd.getPropertyValues();
        if (pvs == null || pvs.size() == 0) {
            return;
        }
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        try {
            for (PropertyValue pv : pvs) {
                String propertyName = pv.getName();
                Object originalValue = pv.getValue();
                Object resolveValue = resolver.resolveValueIfNecessary(originalValue);

                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor pd : descriptors) {
                    if (pd.getName().equals(propertyName)) {
                        Object convertValue = converter.convertIfNeccessary(resolveValue, pd.getPropertyType());
                        pd.getWriteMethod().invoke(bean, convertValue);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new BeanCreationException("Failed to obtain BeanInfo for class [" + bd.getBeanClassName() + "]", e);
        }

    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader == null ? ClassUtils.getDefaultClassLoader() : this.beanClassLoader;
    }

    @Override
    public void addBeanPostProcessor(BeanPostProcessor postProcessor) {
        beanPostProcessors.add(postProcessor);
    }

    @Override
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    @Override
    public Object resolveDependency(DependencyDescriptor descriptor) {
        Class<?> typeToMatch = descriptor.getDependencyType();
        for (BeanDefinition bd : beanMap.values()) {
            resolveBeanClass(bd);
            Class<?> beanClass = bd.getBeanClass();
            //typeToMatch是否与beanClass相同或为beanClass的父类
            if (typeToMatch.isAssignableFrom(beanClass)) {
                return this.getBean(bd.getID());
            }
        }

        return null;
    }

    private void resolveBeanClass(BeanDefinition bd) {
        if (bd.hasBeanClass()) {
            return;
        }else {
            try {
                bd.resolveBeanClass(this.getBeanClassLoader());
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("can't load class:"+bd.getBeanClassName());
            }
        }
    }

    @Override
    public Class<?> getType(String name) {
        BeanDefinition bd = this.getBeanDefinition(name);
        if(bd == null){
            throw new NoSuchBeanDefinitionException(name);
        }
        resolveBeanClass(bd);
        return bd.getBeanClass();
    }
}
