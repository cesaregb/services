package com.il.sod.config.spring;

import static com.il.sod.config.Constants.SPRING_PROFILE_DEVELOPMENT;
import static com.il.sod.config.Constants.SPRING_PROFILE_DOCKER;
import static com.il.sod.config.Constants.SPRING_PROFILE_LOCAL;
import static com.il.sod.config.Constants.SPRING_PROFILE_PRODUCTION;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.il.sod.config.Constants;

@Configuration
@PropertySources(value = { @PropertySource(value = "classpath:project-config.properties") }) // adding global properties to env.
public class PropertiesConfiguration {
	final static Logger LOGGER = LoggerFactory.getLogger(PropertiesConfiguration.class);
	
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
    
    @Bean(name="dataSourceInfo")
    public static Map<String, String> dataSourceInfo(){
    	HashMap<String, String> map = new HashMap<>();
    	
    	
    	
    	map.put("valid", "0");
    	if (System.getenv(Constants.DOCKER_ENV_FLAG) != null){
    		LOGGER.info("***************************");
        	LOGGER.info("getting data from env: " );
    		LOGGER.info(System.getenv(Constants.PROPERTY_NAME_DB_URL) 
    						+ " \n" + System.getenv(Constants.PROPERTY_NAME_DB_USER));
        	LOGGER.info("***************************");
    		map.put("valid", "1");
    		map.put(Constants.PROPERTY_NAME_DB_URL, System.getenv(Constants.PROPERTY_NAME_DB_URL));
    		map.put(Constants.PROPERTY_NAME_DB_USER, System.getenv(Constants.PROPERTY_NAME_DB_USER));
    		map.put(Constants.PROPERTY_NAME_DB_PASSWORD, System.getenv(Constants.PROPERTY_NAME_DB_PASSWORD));
    	}
    	return map;
    }
    
    
    public static PropertySourcesPlaceholderConfigurer getPropertiesByFile(String file) {
    	PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    	configurer.setLocation(new ClassPathResource(file));
    	return configurer;
    }
}
