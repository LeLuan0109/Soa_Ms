package com.safalifter.configserver;

import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

@SpringBootApplication
@EnableConfigServer
@Slf4j
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

//    @Bean
//    public ApplicationRunner logAllPropertySources(ConfigurableEnvironment env) {
//        return args -> {
//            log.info("===== LOGGING PROPERTIES FROM application.properties SOURCES =====");
//
//            for (PropertySource<?> propertySource : env.getPropertySources()) {
//                String name = propertySource.getName();
//
//                // Chỉ log những nguồn từ application.properties
//                if (name.contains("file:./config") || name.contains("applicationConfig: [classpath:/application.properties]")) {
//                    log.info("Source: {}", name);
//
//                    Object source = propertySource.getSource();
//                    if (source instanceof Map<?, ?> map) {
//                        map.forEach((key, value) -> log.info("{} = {}", key, value));
//                    }
//                }
//            }
//
//            log.info("===== END LOGGING PROPERTIES =====");
//        };
//    }

}
