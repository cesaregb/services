package com.il.sod.test.model.entities;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.dto.db.SpecDTO;
import com.il.sod.test.config.SpringTestConfiguration;

public class SpecsDaoTest extends SpringTestConfiguration {

	@Autowired
	SpecRepository specRepository;

	@Test
	public void test() {
		List<Spec> entityList = specRepository.findAll();
		System.out.println("size 1: " + entityList.size());
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		System.out.println("size: " + list.size());
	}
}