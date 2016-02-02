package com.il.sod.config.spring;

import static com.il.sod.config.Constants.PROPERTY_NAME_DATABASE_DRIVER;
import static com.il.sod.config.Constants.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;

import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.il.sod.config.Constants;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.il.sod.db.model.repositories")
public class PersistenceContext {
	final static Logger LOGGER = LoggerFactory.getLogger(PersistenceContext.class);

	@Resource
	private Environment env;

	@Value("${db.url}")
	private String dbUrl;

	@Value("${db.username}")
	private String username;

	@Value("${db.password}")
	private String password;

	@Resource
	@Qualifier("dataSourceInfo")
	private Map<String, String> dataSourceInfo;
	
	// @Bean(destroyMethod = "close")
	@Bean
	DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
		if (dataSourceInfo != null 
				&& (dataSourceInfo.containsKey("valid") 
						&& dataSourceInfo.get("valid") == "1")) {
			dataSource.setUrl(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_URL));
			dataSource.setUsername(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_USER));
			dataSource.setPassword(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_PASSWORD));
		}else{
			dataSource.setUrl(dbUrl);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
		}
		LOGGER.info("******** DB Info");
		LOGGER.info("dbUrl: " + dataSource.getUrl());
		LOGGER.info("username: " + dataSource.getUsername());
		LOGGER.info("*************");

		return dataSource;
	}

	/**
	 * The method that configures the entity manager factory
	 */
	@Bean
	LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactoryBean.setPackagesToScan(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);

		Properties jpaProperties = new Properties();

		jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
		// jpaProperties.put("hibernate.hbm2ddl.auto",
		// env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		// jpaProperties.put("hibernate.format_sql",
		// env.getRequiredProperty("hibernate.format_sql"));

		entityManagerFactoryBean.setJpaProperties(jpaProperties);

		return entityManagerFactoryBean;
	}

	/**
	 * The method that configures the transaction manager
	 */
	@Bean
	JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);
		return transactionManager;
	}
}