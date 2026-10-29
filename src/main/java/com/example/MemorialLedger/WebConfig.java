package com.example.MemorialLedger;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploads").toUri().toString());

        registry.addResourceHandler("/uploadsOrnaments/**")
                .addResourceLocations(Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploadsOrnaments").toUri().toString());
        registry.addResourceHandler("/svg/**")
                .addResourceLocations(Paths.get("C:/Didi/JAVA PROJECTS/MemorialLedger/uploadsSvg").toUri().toString());
    }
}