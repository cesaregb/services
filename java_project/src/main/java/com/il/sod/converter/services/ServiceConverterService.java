package com.il.sod.converter.services;


import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.mapper.UIMapper;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.parse.UIServiceDTO;
import com.il.sod.rest.dto.parse.UISpecDTO;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class ServiceConverterService {

  public ServiceDTO convert(Service entity) {
    // TODO, do we need to ajust something..
    ServiceDTO result = ServiceMapper.INSTANCE.map(entity);
//		Set<SpecDTO> specs = entity.getServiceSpecs().stream().map(item -> SpecsMapper.INSTANCE.map(item.getSpec())).collect(Collectors.toSet());
//		result.setSpecs(specs);
    return result;
  }

  public UIServiceDTO convert2UI(Service entity) {
    UIServiceDTO result = UIMapper.INSTANCE.map(entity);
    List<UISpecDTO> specs = entity.getServiceSpecs().stream().map(item -> convert2UI(item)).collect(Collectors.toList());
    result.setSpecs(specs);
    return result;
  }

  public UISpecDTO convert2UI(ServiceSpec entity) {
    UISpecDTO isd = new UISpecDTO();
    isd.setIdSpecs(entity.getSpec().getIdSpecs());
    isd.setQuantity(entity.getQuantity());
    isd.setValue(entity.getSelectedValue());
    return isd;
  }

}

