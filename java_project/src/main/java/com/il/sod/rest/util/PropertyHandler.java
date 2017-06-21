package com.il.sod.rest.util;

import com.il.sod.config.Constants;
import com.il.sod.exception.SODAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyHandler {
	final static Logger LOGGER = LoggerFactory.getLogger(PropertyHandler.class);
	private static PropertyHandler instance = null;
	private static final String FILE_CONFIG_NAME = "project-config.properties";
	private static final String FILE_ENV_NAME = "config" + File.separatorChar + "application-";

	private Properties props = null;

	private PropertyHandler() throws Exception {
		try {
			this.props = new Properties();
			this.props.putAll(readFile(FILE_CONFIG_NAME));
			String fileEnvName = FILE_ENV_NAME;
			if (System.getProperty("spring.profiles.active") != null){
				fileEnvName += System.getProperty("spring.profiles.active") + ".properties";

			}else if(System.getenv(Constants.ENV_APP_PROFILE) != null){
				fileEnvName += System.getenv(Constants.ENV_APP_PROFILE) + ".properties";

			}else{
				fileEnvName += "local.properties";

			}
			this.props.putAll(readFile(fileEnvName));
		} catch (IOException e) {
			LOGGER.error("Error reading properties", e);
			throw new SODAPIException("Error reading properties", e);
		}
	}
	
	private static Properties readFile(String file) throws IOException{
		Properties p = new Properties();
		InputStream inputStream = PropertyHandler.class.getClassLoader().getResourceAsStream(file);
		p.load(inputStream);
		inputStream.close();
		return p;
	}
	public static PropertyHandler getInstance(){
		synchronized (PropertyHandler.class){
			if (instance == null){
				try{
					instance = new PropertyHandler();
				}catch(Exception e){
					LOGGER.error("Error reading properties", e);
				}
			}
		}
		return instance;
	}

	public String getValue(String propKey) {
		return this.props.getProperty(propKey);
	}
}
