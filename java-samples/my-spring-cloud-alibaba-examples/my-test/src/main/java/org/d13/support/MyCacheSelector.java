package org.d13.support;

import org.d13.annotation.EnableMyCache;
import org.d13.service.impl.LocalServicempl;
import org.d13.service.impl.RedisServicempl;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.text.MessageFormat;
import java.util.Map;


public class MyCacheSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        Map<String, Object> annotationAttributes =
                importingClassMetadata.getAnnotationAttributes(EnableMyCache.class.getName());
        //通过 不同type注入不同的缓存到容器中
        CacheType type = (CacheType) annotationAttributes.get("type");
        switch (type) {
            case LOCAL: {
                return new String[]{LocalServicempl.class.getName()};
            }
            case REDIS: {
                return new String[]{RedisServicempl.class.getName()};
            }
            default: {
                throw new RuntimeException(MessageFormat.format("unsupport cache type {0}", type.toString()));
            }
        }
    }
}