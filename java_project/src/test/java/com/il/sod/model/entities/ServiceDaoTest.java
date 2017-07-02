package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.repositories.ServiceCategoryRepository;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.dto.db.ServiceCategoryDTO;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

public class ServiceDaoTest extends SpringTestConfiguration {

  private final static Logger LOGGER = getLogger(ServiceDaoTest.class);

  @Autowired
  ServiceCategoryRepository serviceCategoryRepository;

  @Autowired
  SpecificObjectsConverterService specificObjectsConverterService;


  @Test
  public void testServiceCategory() {
    List<ServiceCategory> entities = serviceCategoryRepository.findAll();
    Assert.assertThat("Found any category", entities.size(), Matchers.greaterThan(0));
    LOGGER.info("entities size: {} ", entities.size());

    List<ServiceCategoryDTO> result = entities.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toList());
    Assert.assertThat("Converter not working ", entities.size(), Matchers.is(result.size()));
  }

  @Test
  public void testWServiceCategoryConverter() {
    List<ServiceCategory> entities = serviceCategoryRepository.findAll();
    System.out.println("entities: " + entities.size());
    Assert.assertThat("Found any category", entities.size(), Matchers.greaterThan(0));
    List<WServiceCategoryDTO> result = entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());
    Assert.assertThat("Converter not working ", entities.size(), Matchers.is(result.size()));
    System.out.println("result: " + result.size());
  }

}