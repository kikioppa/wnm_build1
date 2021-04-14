package com.example.dodgema.configration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;




@Getter
@Configuration
@PropertySource("classpath:application.yml")
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String fileRealPath;
    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/zzz/**")
                .addResourceLocations("file:///"+fileRealPath);
    }
}