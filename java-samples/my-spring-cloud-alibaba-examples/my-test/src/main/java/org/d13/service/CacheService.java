package org.d13.service;

import org.springframework.stereotype.Service;

@Service
public interface CacheService {
    void setData(String key);
}
