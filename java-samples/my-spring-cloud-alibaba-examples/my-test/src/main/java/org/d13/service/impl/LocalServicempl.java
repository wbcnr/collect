package org.d13.service.impl;

import org.d13.service.CacheService;

public class LocalServicempl implements CacheService {

    @Override
    public void setData(String key) {
        System.out.println("本地存储存储数据成功 key= " + key);
    }
}
