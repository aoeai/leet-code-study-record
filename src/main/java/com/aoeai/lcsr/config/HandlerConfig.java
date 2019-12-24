package com.aoeai.lcsr.config;

import com.aoeai.lcsr.handler.ErrorPageRegistrarHandler;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 助手工具配置
 */
@Configuration
public class HandlerConfig {

    @Bean
    public ErrorPageRegistrar errorPageRegistrar(){
        return new ErrorPageRegistrarHandler();
    }
}
