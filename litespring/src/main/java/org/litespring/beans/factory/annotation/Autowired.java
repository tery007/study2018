package org.litespring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author wanglei
 * @description
 * @date Created on 2019/2/5
 **/
@Target({ElementType.CONSTRUCTOR,ElementType.ANNOTATION_TYPE,ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    boolean required() default true;

}
