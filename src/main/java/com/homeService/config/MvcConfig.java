package com.homeService.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${il.path.images}")
    private String pathImages;
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("auth/login/index");
        registry.addViewController("/admin").setViewName("admin/info/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/com/**").addResourceLocations("content/commons/").setCachePeriod(0);
        registry.addResourceHandler("/resource/**").addResourceLocations("content/pages/").setCachePeriod(0);
        System.err.println(pathImages);
//        registry.addResourceHandler("/ProductImages/**").addResourceLocations("content/images/").setCachePeriod(0);
        registry.addResourceHandler("/ProductImages/**").addResourceLocations("file:///" + pathImages).setCachePeriod(0); // For Windows
//        registry.addResourceHandler("/ProductImages/**").addResourceLocations("file://" + pathImages).setCachePeriod(0); // For Linux
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configure) {
        configure.enable();
    }
}
