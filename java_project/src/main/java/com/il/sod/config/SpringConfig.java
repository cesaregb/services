package com.il.sod.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy // we set the aspectJ annotation here to be setted in the same context as the controllers. 
@ComponentScan({
        "com.il.sod.config.spring",  // spring configuration for mvc
        "com.il.sod.services", // services
        "com.il.sod.converter.services", // converter services
        "com.il.sod.db.dao", // DAO Services
        "com.il.sod.aop", // aop
        "com.il.sod.rest.api" // services
})
public class SpringConfig {

}