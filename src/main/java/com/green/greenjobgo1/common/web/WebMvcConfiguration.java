package com.green.greenjobgo1.common.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Value("${file.dir}")
    private String FILE_DIR;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(registry);
    }
    private void exposeDirectory(ResourceHandlerRegistry reg) {
        Path uploadDir = Paths.get(FILE_DIR);
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        if (FILE_DIR.startsWith("../")) FILE_DIR = FILE_DIR.replace("../", "");
        log.info("uploadPath {}", uploadPath);

        reg.addResourceHandler( "/img/**")
                .addResourceLocations(String.format("file:%s/", FILE_DIR));

        reg.addResourceHandler( "/**")
                .addResourceLocations("classpath:/static/**")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        Resource requestedResource = location.createRelative(resourcePath);
                        // If we actually hit a file, serve that. This is stuff like .js and .css files.
                        if (requestedResource.exists() && requestedResource.isReadable()) {
                            return requestedResource;
                        }
                        // Anything else returns the index.
                        return new ClassPathResource("/static/index.html");
                    }
                });
    }
}

