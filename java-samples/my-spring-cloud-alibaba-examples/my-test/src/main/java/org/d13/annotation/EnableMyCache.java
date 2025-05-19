package org.d13.annotation;

import org.d13.support.CacheType;
import org.d13.support.MyCacheSelector;
import org.springframework.context.annotation.Import;
import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MyCacheSelector.class)
public @interface EnableMyCache {
    CacheType type() default CacheType.REDIS;
}