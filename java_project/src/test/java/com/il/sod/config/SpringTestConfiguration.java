package com.il.sod.config;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringConfig.class, loader=AnnotationConfigContextLoader.class)
@ContextConfiguration(locations={"classpath*:applicationContextTest.xml"})
@ActiveProfiles("local")
public abstract class SpringTestConfiguration {}
