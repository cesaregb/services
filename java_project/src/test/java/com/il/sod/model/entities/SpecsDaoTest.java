package com.il.sod.model.entities;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.dto.db.SpecDTO;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class SpecsDaoTest extends SpringTestConfiguration {

	@Autowired
	SpecRepository specRepository;
	
	@Test
	public void testConverter() {
		List<Spec> entityList = specRepository.findAll();
		System.out.println("size 1: " + entityList.size());
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		Assert.assertThat("should be equal", entityList.size(), Matchers.is(list.size()));
	}
}