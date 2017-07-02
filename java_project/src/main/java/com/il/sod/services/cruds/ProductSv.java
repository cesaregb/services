package com.il.sod.services.cruds;

import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 7/2/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class ProductSv {

  @Autowired
  ProductTypeRepository productTypeRepository;

  public List<ProductTypeDTO> getProductTypeList(Integer idServiceType) throws SODAPIException {
    List<ProductType> entityList;
    if (idServiceType != null && idServiceType > 0 ){
      entityList = productTypeRepository.findByServiceType(idServiceType);
    }else{
      entityList = productTypeRepository.findAll();
    }

    return entityList.stream()
            .map(ProductMapper.INSTANCE::map)
            .filter(DeletablePredicate.isActive())
            .collect(Collectors.toList());
  }
}
