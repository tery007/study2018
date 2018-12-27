package org.litespring.beans;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/26 下午11:25
 **/
public class BeansException extends RuntimeException {

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
