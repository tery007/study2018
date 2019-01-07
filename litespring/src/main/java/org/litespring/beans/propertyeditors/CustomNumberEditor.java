package org.litespring.beans.propertyeditors;

import org.litespring.util.NumberUtils;
import org.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;
import java.text.NumberFormat;

/**
 * @author tery
 * @description: 自定义数字编辑工具
 * @date Created on 2019/1/6 上午11:54
 **/
public class CustomNumberEditor extends PropertyEditorSupport {

    private final Class<? extends Number> numberClass;
    private final NumberFormat            numberFormat;
    private final boolean                 allowEmpty;

    public CustomNumberEditor(Class<? extends Number> numberClass, boolean allowEmpty) {
        this(numberClass, null, allowEmpty);
    }

    public CustomNumberEditor(Class<? extends Number> numberClass, NumberFormat numberFormat, boolean allowEmpty) {

        if (numberClass == null || !Number.class.isAssignableFrom(numberClass)) {
            throw new IllegalArgumentException("Property class must be subclass of Number");
        }
        this.numberClass = numberClass;
        this.numberFormat = numberFormat;
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String strVal) {
        if (this.allowEmpty && !StringUtils.hasText(strVal)) {
            setValue(null);
        } else if (this.numberFormat != null) {
            setValue(NumberUtils.parseNumber(strVal, this.numberClass, this.numberFormat));
        }else {
            setValue(NumberUtils.parseNumber(strVal,this.numberClass));
        }
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        if (value == null) {
            return "";
        }
        if (this.numberFormat != null) {
            return numberFormat.format(value);
        }else {
            return super.getAsText();
        }
    }
}
