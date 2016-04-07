package com.il.sod.config;

public class Constants {
	private Constants() {
    }

    public static final String SPRING_PROFILE_LOCAL = "local";
    public static final String SPRING_PROFILE_DOCKER = "docker";
    public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
    public static final String SPRING_PROFILE_PRODUCTION = "prod";
    public static final String SPRING_PROFILE_FAST = "fast";
    public static final String SPRING_PROFILE_CLOUD = "cloud";
    public static final String SYSTEM_ACCOUNT = "system";
    
    
    public static final String PROPERTY_NAME_DATABASE_DRIVER = "db.driver";

    public static final String PROPERTY_NAME_DB_URL = "DB_URL";
    public static final String PROPERTY_NAME_DB_USER = "DB_USER";
    public static final String PROPERTY_NAME_DB_PASSWORD = "DB_PASSWORD";
//    public static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";
//    public static final String PROPERTY_NAME_DATABASE_URL = "db.url";
//    public static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";

	public static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.il.sod.db.model.entities";
	
	public static final String ENV_APP_PROFILE = "APP_PROFILE";

	public static final String DOCKER_ENV_FLAG = "DB_URL";
	
	public static final int SPEC_TYPE_PRODUCT = 2;
	public static final int SPEC_TYPE_VALUES = 1;
	
	
	// order status 
	public static final int ORDER_CREATED = 0; 
	public static final int ORDER_PICKED_UP = 1; 
	public static final int ORDER_STARTED = 2; 
	public static final int ORDER_FINISHED = 3; 
	public static final int ORDER_DELIVERED = 4; 
	
	
}
