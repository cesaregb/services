package com.il.sod.config.spring;

import com.il.sod.config.Constants;
import com.typesafe.config.Config;
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

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import static com.il.sod.config.Constants.PROPERTY_NAME_DATABASE_DRIVER;
import static com.il.sod.config.Constants.PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.il.sod.db.model.repositories")
public class PersistenceContext {
  private final static Logger LOGGER = LoggerFactory.getLogger(PersistenceContext.class);
  private static final String SECURITY_DB_USER = "security.dbUser";
  private static final String SECURITY_DB_PASSWORD = "security.dbPassword";
  private static final String GENERAL_DB_URL = "general.dbUrl";

  @Resource
  private Environment env;

  @Value("${db.url}")
  private String dbUrl;

  @Resource
  @Qualifier("dataSourceInfo")
  private Map<String, String> dataSourceInfo;

  @Resource
  @Qualifier("envConfig")
  private Config envConfig;

  // @Bean(destroyMethod = "close")
  @Bean
  DataSource dataSource() {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
    if (dataSourceInfo != null
            && (dataSourceInfo.containsKey("valid")
            && Objects.equals(dataSourceInfo.get("valid"), "1"))) {
      // information from docker
      dataSource.setUrl(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_URL));
      dataSource.setUsername(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_USER));
      dataSource.setPassword(dataSourceInfo.get(Constants.PROPERTY_NAME_DB_PASSWORD));
    } else {
      final String username = Constants.envConfig.getString(SECURITY_DB_USER);
      final String password = Constants.envConfig.getString(SECURITY_DB_PASSWORD);
      final String dbUrlInfo = (Constants.envConfig.getString(GENERAL_DB_URL) != null) ? Constants.envConfig.getString(GENERAL_DB_URL) : dbUrl;
      dataSource.setUrl(dbUrlInfo);
      dataSource.setUsername(username);
      dataSource.setPassword(password);
    }
    LOGGER.info("******** DB Info");
    LOGGER.info("dbUrl: " + dataSource.getUrl());
    LOGGER.info("username: " + dataSource.getUsername());
    LOGGER.info("username: " + dataSource.getPassword());
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
    // Important to avoid having it in console all the time...!!!!
//		jpaProperties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
//		jpaProperties.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
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