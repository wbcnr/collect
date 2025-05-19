package org.d13.service.impl;

import org.d13.annotation.Loggable;
import org.d13.service.CustomAnnotationService;

/**
 * 标签:《自定义注解处理器2025-4-14 10:46:36》
 */
public class CustomAnnotationServiceImpl implements CustomAnnotationService {

    @Override
    @Loggable(message = "User login attempt", logTime = true)
    public void login( String username ) {
        System.out.println ("处理实际业务-login + username: " + username);
    }

    @Override
    @Loggable(message = "User logout attempt", logTime = true)
    public void logout( String username ) {
        System.out.println ("处理实际业务-logout + username: " + username);
    }
}
