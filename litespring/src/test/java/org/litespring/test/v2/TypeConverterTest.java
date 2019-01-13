package org.litespring.test.v2;

import org.junit.Assert;
import org.junit.Test;
import org.litespring.beans.SimpleTypeConverter;
import org.litespring.beans.TypeConverter;
import org.litespring.beans.TypeMismatchException;

/**
 * @author tery
 * @description
 * @date Created on 2019/1/12 上午11:27
 **/
public class TypeConverterTest {

    @Test
    public void testTypeConvertString2int() {
        TypeConverter tc = new SimpleTypeConverter();
        Integer i = tc.convertIfNeccessary("3", Integer.class);
        Assert.assertEquals(i.intValue(), 3);

        try {
            tc.convertIfNeccessary("3.1", Integer.class);
        } catch (TypeMismatchException e) {
            return;
        }
        Assert.fail();
    }
}
