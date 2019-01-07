package org.litespring.beans;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/29 上午11:36
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }
}
