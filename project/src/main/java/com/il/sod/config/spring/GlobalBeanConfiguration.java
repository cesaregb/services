package com.il.sod.config.spring;

import com.il.sod.config.Constants;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class GlobalBeanConfiguration {

	final static Logger LOGGER = LoggerFactory.getLogger(GlobalBeanConfiguration.class);

	@Bean(name="dataSourceInfo")
	// Method to override configuratoin from env vars!
	public static Map<String, String> dataSourceInfo(){
		HashMap<String, String> map = new HashMap<>();

		map.put("valid", "0");
		if (System.getenv(Constants.DOCKER_ENV_FLAG) != null){
			LOGGER.info("***************************");
			LOGGER.info("getting data from env: " );
			LOGGER.info(System.getenv(Constants.PROPERTY_NAME_DB_URL) + " \n" + System.getenv(Constants.PROPERTY_NAME_DB_USER));
			LOGGER.info("***************************");
			map.put("valid", "1");
			map.put(Constants.PROPERTY_NAME_DB_URL, System.getenv(Constants.PROPERTY_NAME_DB_URL));
			map.put(Constants.PROPERTY_NAME_DB_USER, System.getenv(Constants.PROPERTY_NAME_DB_USER));
			map.put(Constants.PROPERTY_NAME_DB_PASSWORD, System.getenv(Constants.PROPERTY_NAME_DB_PASSWORD));
		}
		return map;
	}

	@Bean(name="envConfig")
	public static Config envConfig(){
		return ConfigFactory.load().getConfig(Constants.COM_IL_SOD_APPLICATION);
	}

}
