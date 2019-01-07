package org.litespring.beans.factory.config;

/**
 * @author wangjunkai
 * @description
 * @date Created on 2018/12/30 下午10:37
 **/
public class TypedStringValue {
    private String value;

    public TypedStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
