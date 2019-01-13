package org.litespring.beans;

import org.litespring.beans.propertyeditors.CustomBooleanEditor;
import org.litespring.beans.propertyeditors.CustomNumberEditor;
import org.litespring.util.ClassUtils;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/12 下午12:47
 **/
public class SimpleTypeConverter implements TypeConverter {

    private final static Map<Class<?>, PropertyEditor> defaultEditorMap = new HashMap<>();
    @Override
    public <T> T convertIfNeccessary(Object value, Class<T> requiredType) {

        if (ClassUtils.isAssignableValue(requiredType, value)) {
            return (T)value;
        }
        if (value instanceof String) {
            PropertyEditor editor = findDefaultEditor(requiredType);
            try {
                editor.setAsText((String) value);
            } catch (IllegalArgumentException e) {
                throw new TypeMismatchException(value, requiredType);
            }
            return (T) editor.getValue();
        } else {
            throw new RuntimeException("can`t convert value:" + value + " to type:" + requiredType);
        }
    }

    private PropertyEditor findDefaultEditor(Class<?> requiredType) {
        PropertyEditor editor = this.getDefaultEditor(requiredType);
        if (editor == null) {
            throw new RuntimeException("can not find editor for type:" + requiredType);
        }
        return editor;
    }

    private PropertyEditor getDefaultEditor(Class<?> type) {
        if (defaultEditorMap.size() == 0) {
            initPropertyEditor();
        }
        return defaultEditorMap.get(type);
    }

    private void initPropertyEditor() {
        defaultEditorMap.put(Integer.class, new CustomNumberEditor(Integer.class, true));
        defaultEditorMap.put(int.class, new CustomNumberEditor(Integer.class, false));
        defaultEditorMap.put(Boolean.class, new CustomBooleanEditor(true));
        defaultEditorMap.put(boolean.class, new CustomBooleanEditor(false));
    }

}
