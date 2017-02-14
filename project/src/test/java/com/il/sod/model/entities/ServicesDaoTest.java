package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.db.model.repositories.ServiceCategoryRepository;
import com.il.sod.db.model.repositories.ServiceTypeRepository;
import com.il.sod.db.model.repositories.SpecRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

public class ServicesDaoTest extends SpringTestConfiguration{
	private final static Logger LOGGER = getLogger(ServiceDaoTest.class);
	
	@Autowired
	ServiceTypeRepository serviceTypeRepository;

	@Autowired
	ServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	SpecRepository specRepository;

	@Autowired
	ProductTypeRepository productTypeRepository;

	@Ignore
	@Test
	public void testCreateAndAddSpec(){
		String name = RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "[test]";

		ServiceCategory serviceCategory = serviceCategoryRepository.findOne(1);
		ServiceType serviceType = new ServiceType(name,"desc",0, 0, serviceCategory, false);
		serviceType = serviceTypeRepository.save(serviceType);
		LOGGER.info("serviceType: {}", serviceType);
		System.out.format("serviceType: [%s] %n", serviceType);

		Spec spec = new Spec(name, "desc", true, 0);
		specRepository.save(spec);

		serviceType.addSpec(spec);
		serviceTypeRepository.save(serviceType);

		ProductType productType = new ProductType(name, "desc");
		productTypeRepository.save(productType);

		serviceType.addProductType(productType);
		serviceTypeRepository.save(serviceType);
		System.out.format("serviceType: [%s] %n", serviceType);

		serviceType.removeSpec(spec);
		serviceTypeRepository.save(serviceType);
		Assert.assertTrue("Specs should be empty", serviceType.getSpecs().isEmpty());
		// get it from the db...
		serviceType = serviceTypeRepository.findOne(serviceType.getId());
		Assert.assertTrue("Specs should be empty", serviceType.getSpecs().isEmpty());

		System.out.format("serviceType: [%s] %n", serviceType.getSpecs().size());

	}

}