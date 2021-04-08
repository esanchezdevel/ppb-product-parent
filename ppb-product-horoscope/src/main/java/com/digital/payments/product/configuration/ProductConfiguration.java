package com.digital.payments.product.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.digital.payments.product.interceptor.StatsInterceptor;

@Configuration
public class ProductConfiguration implements WebMvcConfigurer {

	@Autowired
	private StatsInterceptor statsInterceptor;
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(statsInterceptor);
    }
}
