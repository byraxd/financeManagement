package com.example.financeManagement.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContextInitializer {

    private final ApplicationContext applicationContext;

    @Autowired
    public SpringContextInitializer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @PostConstruct
    public void init() {
        SpringContext.setApplicationContext(applicationContext);
    }
}
