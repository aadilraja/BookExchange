package app.api.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/api/books/images/**")
                .addResourceLocations(
                        "classpath:/static/ImagesBook/",
                        "file:src/main/resources/static/ImagesBook/",
                        "classpath:/static.ImagesBook/" )
                .setCachePeriod(3600)
                .resourceChain(true);
    }
}
