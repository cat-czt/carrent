package com.qc.information.config;

import java.lang.annotation.*;

/**
 * @Author: czt
 * @Date: 18-10-31 上午10:30
 */
@Inherited
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthPassport {
    boolean value() default true;
}