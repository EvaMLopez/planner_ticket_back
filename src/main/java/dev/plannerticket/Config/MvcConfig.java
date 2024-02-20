package dev.plannerticket.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//Configuración que dice a Spring dónde buscar las imágenes y cómo mapear las solicitudes.
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**")
                .addResourceLocations("file:src/main/resources/static/images/");
                // .addResourceLocations("classpath:/static/images/");
    }
}

