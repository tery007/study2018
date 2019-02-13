package org.litespring.beans.factory.support;

import org.litespring.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/28 上午11:02
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    public final Map<String, Object> singletonInstances = new ConcurrentHashMap<>(1024);

    @Override
    public void singletonBeanRegister(String beanName, Object singletonObject) {
        Object oldBean = singletonInstances.get(beanName);
        if (oldBean != null) {
            throw new IllegalStateException("Could not register object [" + singletonObject +
                    "] under bean name '" + beanName + "': there is already object [" + oldBean + "] bound");
        }
        singletonInstances.put(beanName, singletonObject);
    }

    @Override
    public Object getSingletonBean(String beanName) {
        return singletonInstances.get(beanName);
    }
}
