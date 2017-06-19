package com.il.sod.services.crud;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.rest.dto.db.*;
import com.il.sod.services.cruds.ServicesSv;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
public class ServicesSvTest extends SpringTestConfiguration {

	@Autowired
	ServicesSv servicesSv;

	@Test
	public void testAddSpecs() throws Exception{
		List<SpecDTO> list = new ArrayList<>();
		list.add(new SpecDTO(11));
		list.add(new SpecDTO(12));
		servicesSv.addSpecs(8, list);
		ServiceTypeDTO dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getSpecs().size() == 2);

		list = new ArrayList<>();
		list.add(new SpecDTO(11));
		list.add(new SpecDTO(12));
		list.add(new SpecDTO(13));
		servicesSv.addSpecs(8, list);
		dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getSpecs().size() == 3);

		list = new ArrayList<>();
		list.add(new SpecDTO(11));
		servicesSv.addSpecs(8, list);
		dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getSpecs().size() == 1);
	}

	@Test
	public void testAddProductTypes() throws Exception{
		List<ProductTypeDTO> list = new ArrayList<>();
		list.add(new ProductTypeDTO(8));
		servicesSv.addProducts(8, list);
		ServiceTypeDTO dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getProductTypes().size() == 1);

		list = new ArrayList<>();
		list.add(new ProductTypeDTO(8));
		list.add(new ProductTypeDTO(9));
		servicesSv.addProducts(8, list);
		dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getProductTypes().size() == 2);
	}


	@Test
	public void testAddServiceTypeTask() throws Exception{
		List<ServiceTypeTaskDTO> list = new ArrayList<>();
		list.add(new ServiceTypeTaskDTO(new TaskDTO(1)));
		list.add(new ServiceTypeTaskDTO(new TaskDTO(2)));
		servicesSv.addServiceTypeTask(8, list);
		ServiceTypeDTO dto = servicesSv.getServiceTypeList(8).get(0);
		System.out.format("dto.getServiceTypeTasks().size(): [%s] %n", dto.getServiceTypeTasks().size());
		Assert.assertTrue(dto.getServiceTypeTasks().size() == 2);

		list = new ArrayList<>();
		list.add(new ServiceTypeTaskDTO(new TaskDTO(1)));
		list.add(new ServiceTypeTaskDTO(new TaskDTO(2)));
		list.add(new ServiceTypeTaskDTO(new TaskDTO(3)));
		servicesSv.addServiceTypeTask(8, list);
		dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getServiceTypeTasks().size() == 3);

		list = new ArrayList<>();
		list.add(new ServiceTypeTaskDTO(new TaskDTO(1)));
		servicesSv.addServiceTypeTask(8, list);
		dto = servicesSv.getServiceTypeList(8).get(0);
		Assert.assertTrue(dto.getServiceTypeTasks().size() == 1);
	}

}
