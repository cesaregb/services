package com.il.sod.config.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import static com.il.sod.config.Constants.*;

@Configuration
@PropertySources(value = {@PropertySource(value = "classpath:project-config.properties")})
// adding global properties to env.
public class PropertiesConfiguration {

  @Bean
  @Profile(SPRING_PROFILE_LOCAL)
  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
    String propertiesFilename = "config/application-local.properties";
    return getPropertiesByFile(propertiesFilename);
  }

  @Bean
  @Profile(SPRING_PROFILE_DOCKER)
  public static PropertySourcesPlaceholderConfigurer propertyDockerPlaceholderConfigurer() {
    String propertiesFilename = "config/application-docker.properties";
    return getPropertiesByFile(propertiesFilename);
  }

  @Bean
  @Profile(SPRING_PROFILE_DEVELOPMENT)
  public static PropertySourcesPlaceholderConfigurer propertyDevPlaceholderConfigurer() {
    String propertiesFilename = "config/application-dev.properties";
    return getPropertiesByFile(propertiesFilename);
  }

  @Bean
  @Profile(SPRING_PROFILE_PRODUCTION)
  public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
    String propertiesFilename = "config/application-prod.properties";
    return getPropertiesByFile(propertiesFilename);
  }

  public static PropertySourcesPlaceholderConfigurer getPropertiesByFile(String file) {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocation(new ClassPathResource(file));
    return configurer;
  }
}
