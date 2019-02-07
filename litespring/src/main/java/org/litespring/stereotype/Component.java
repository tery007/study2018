package org.litespring.stereotype;

import java.lang.annotation.*;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/5
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
