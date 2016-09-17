package com.il.sod.db.dao.impl;

import com.il.sod.db.model.entities.Subproduct;
import com.il.sod.db.model.repositories.SubproductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.aspectj.org.eclipse.jdt.internal.compiler.parser.TheOriginalJDTParserClass.name;

@Service
public class SubproductDAO {
	
	@Autowired
	SubproductRepository subproductRepository;
	
	public List<Subproduct> findByName(String name) {
		return subproductRepository.findByName("%" + name + "%");
	}
	public List<Subproduct> findBySubproductType(int idSubproductType) {
		return subproductRepository.findBySubproductType(idSubproductType);
	}
}
