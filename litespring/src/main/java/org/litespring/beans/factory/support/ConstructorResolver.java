package org.litespring.beans.factory.support;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.litespring.beans.ConstructorArgument;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinition;
import org.litespring.beans.factory.config.ConfigurableBeanFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/3
 **/
public class ConstructorResolver {

    protected final Log                     logger = LogFactory.getLog(getClass());

    private         ConfigurableBeanFactory factory;

    public ConstructorResolver(ConfigurableBeanFactory factory) {
        this.factory = factory;
    }


    public Object autowiedConstructor(final BeanDefinition bd) {
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;
        Class<?> beanClass = null;
        try {
            beanClass = factory.getBeanClassLoader().loadClass(bd.getBeanClassName());
        } catch (Exception e) {
            throw new BeanCreationException( bd.getID(), "Instantiation of bean failed, can't resolve class", e);
        }
        SimpleTypeConverter converter = new SimpleTypeConverter();
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(factory);
        ConstructorArgument argument = bd.getConstructorArgument();
        Constructor<?>[] candicates = beanClass.getConstructors();
        for (int i = 0; i < candicates.length; i++) {
            Constructor<?> candicate = candicates[i];
            Class<?>[] parameterTypes = candicate.getParameterTypes();
            if (argument.getArgumentCount() != parameterTypes.length) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean result = valuesMatchTypes(parameterTypes, argument.getArgumentValues(), argsToUse, converter, resolver);
            if (result) {
                constructorToUse = candicates[i];
                break;
            }
        }
        if (constructorToUse == null) {
            throw new BeanCreationException(bd.getID(), "can't find a apporiate constructor");
        }
        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(bd.getID(), "can't find a create instance using " + constructorToUse);
        }

    }

    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> argumentValues,
                                     Object[] argsToUse,
                                     SimpleTypeConverter converter,
                                     BeanDefinitionValueResolver resolver) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = argumentValues.get(i);
            Object originalValue = valueHolder.getValue();
            try {
                Object resolvedValue = resolver.resolveValueIfNecessary(originalValue);
                Object convertValue = converter.convertIfNeccessary(resolvedValue, parameterTypes[i]);
                argsToUse[i] = convertValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
}
