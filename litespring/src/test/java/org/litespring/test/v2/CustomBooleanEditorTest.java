package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.propertyeditors.CustomBooleanEditor;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/7 下午11:38
 **/
public class CustomBooleanEditorTest {

    @Test
    public void testBooleanEditor() {
        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("yes");
        Object value = editor.getValue();
        Assert.assertTrue(value instanceof Boolean);
        Assert.assertTrue(editor.getAsText().equals("true"));

    }
}
