package org.litespring.beans.factory;

import org.litespring.beans.BeansException;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午11:26
 **/
public class BeanDefinitionStoreException extends BeansException {

    public BeanDefinitionStoreException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
