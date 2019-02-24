package org.litespring.context.support;

import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.annotation.AutowiredAnnotationProcessor;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.Resource;
import org.litespring.util.ClassUtils;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/27 下午3:51
 **/
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory defaultBeanFactory;
    private ClassLoader        classLoader;

    public AbstractApplicationContext(String configFile) {
        defaultBeanFactory = new DefaultBeanFactory();
        Resource resource = this.getResourceByPath(configFile);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultBeanFactory);
        reader.loadBeanDefinitions(resource);
        //初始化defaultBeanFactory中的classLoader，在调用defaultBeanFactory.getBean(beanId)时使用
        defaultBeanFactory.setBeanClassLoader(this.getBeanClassLoader());
        registerBeanPostProcessors(defaultBeanFactory);
    }

    @Override
    public Object getBean(String beanId) {
        return this.defaultBeanFactory.getBean(beanId);
    }

    public BeanDefinition getBeanDefinition(String beanId) {
        return this.defaultBeanFactory.getBeanDefinition(beanId);
    }

    protected abstract Resource getResourceByPath(String configFile);

    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    public ClassLoader getBeanClassLoader() {
        return this.classLoader == null ? ClassUtils.getDefaultClassLoader() : this.classLoader;
    }

    protected void registerBeanPostProcessors(ConfigurableBeanFactory beanFactory) {

        AutowiredAnnotationProcessor postProcessor = new AutowiredAnnotationProcessor();
        postProcessor.setBeanFactory(beanFactory);
        beanFactory.addBeanPostProcessor(postProcessor);

    }

    @Override
    public Class<?> getType(String beanName) {
        return this.defaultBeanFactory.getType(beanName);
    }
}
