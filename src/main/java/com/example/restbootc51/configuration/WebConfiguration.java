package com.example.restbootc51.configuration;

import com.example.restbootc51.interceptor.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final Interceptor interceptor;

    public WebConfiguration(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**").order(2)
                .excludePathPatterns("/user");
    }
}
