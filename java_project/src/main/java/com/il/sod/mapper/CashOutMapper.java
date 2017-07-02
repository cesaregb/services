package com.il.sod.mapper;

import com.il.sod.db.model.entities.CashOut;
import com.il.sod.rest.dto.db.CashOutDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum CashOutMapper implements MapperMarker {

  INSTANCE;

  private final MapperFacade mapperFacade;

  private CashOutMapper() {
    ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();

    BaseMapper.MAPPER_FACTORY.classMap(CashOutDTO.class, CashOut.class)
            .byDefault()
            .register();

    mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
  }

  public CashOut map(CashOutDTO dto) {
    return this.mapperFacade.map(dto, CashOut.class);
  }

  public CashOutDTO map(CashOut entity) {
    return this.mapperFacade.map(entity, CashOutDTO.class);
  }

  public CashOut map(CashOutDTO dto, CashOut entity) {
    this.mapperFacade.map(dto, entity);
    return entity;
  }


}



