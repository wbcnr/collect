package org.d13.service.impl;

import org.d13.service.CacheService;

public class RedisServicempl implements CacheService {

    @Override
    public void setData(String key) {
        System.out.println("redis存储数据成功 key= " + key);
    }
}
