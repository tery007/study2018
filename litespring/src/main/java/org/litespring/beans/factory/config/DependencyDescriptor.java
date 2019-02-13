package org.litespring.beans.factory.config;

import org.litespring.util.Assert;

import java.lang.reflect.Field;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/12
 **/
public class DependencyDescriptor {

    private Field field;

    private boolean required;

    public DependencyDescriptor(Field field, boolean required) {
        Assert.notNull(field, "field must be not null");
        this.field = field;
        this.required = required;
    }


    public Class<?> getDependencyType() {
        if (field != null) {
            return field.getType();
        }
        throw new RuntimeException("only support field dependency");
    }

}
