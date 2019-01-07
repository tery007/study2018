package org.litespring.beans.propertyeditors;

import org.litespring.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/7 下午11:36
 **/
public class CustomBooleanEditor extends PropertyEditorSupport {

    private static final String VALUE_TRUE  = "true";
    private static final String VALUE_FALSE = "false";
    private static final String VALUE_ON    = "on";
    private static final String VALUE_OFF   = "off";
    private static final String VALUE_YES   = "yes";
    private static final String VALUE_NO    = "no";
    private static final String VALUE_ONE   = "1";
    private static final String VALUE_ZERO  = "0";

    private final boolean allowEmpty;

    public CustomBooleanEditor(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    @Override
    public void setAsText(String text) {
        String input = text != null ? text.trim() : null;
        if (this.allowEmpty && !StringUtils.hasText(text)) {
            setValue(null);
        } else if (input.equalsIgnoreCase(VALUE_TRUE) || input.equalsIgnoreCase(VALUE_ON)
                || input.equalsIgnoreCase(VALUE_YES) || input.equalsIgnoreCase(VALUE_ONE)) {
            setValue(Boolean.TRUE);
        } else if (input.equalsIgnoreCase(VALUE_FALSE) || input.equalsIgnoreCase(VALUE_OFF)
                || input.equalsIgnoreCase(VALUE_NO) || input.equalsIgnoreCase(VALUE_ZERO)) {
            setValue(Boolean.FALSE);
        }else {
            throw new IllegalArgumentException("Invalid boolean value【" + text + "】");
        }
    }

    @Override
    public String getAsText() {
        if (getValue().equals(Boolean.TRUE)) {
            return VALUE_TRUE;
        }
        if (getValue().equals(Boolean.FALSE)) {
            return VALUE_FALSE;
        }
        return "";
    }
}
