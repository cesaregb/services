package com.il.sod.mapper;

import com.il.sod.db.model.entities.DistanceInfo;
import com.il.sod.db.model.entities.Store;
import com.il.sod.rest.dto.db.DistanceInfoDTO;
import com.il.sod.rest.dto.db.StoreDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum StoreInfoMapper {

  INSTANCE;
  private final MapperFacade mapperFacade;

  private StoreInfoMapper() {

    ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
    converterFactory.registerConverter("distanceInfoSetConverter", new DistanceInfoSetConverter());

    BaseMapper.MAPPER_FACTORY.classMap(DistanceInfoDTO.class, DistanceInfo.class)
            .field("idStore", "store.idStore")
            .byDefault()
            .register();

    BaseMapper.MAPPER_FACTORY.classMap(StoreDTO.class, Store.class)
            .fieldMap("distanceInfos", "distanceInfos").converter("distanceInfoSetConverter").mapNulls(true).mapNullsInReverse(true).add()
            .byDefault()
            .register();

    mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
  }

  public DistanceInfo map(DistanceInfoDTO input) {
    return this.mapperFacade.map(input, DistanceInfo.class);
  }

  public DistanceInfoDTO map(DistanceInfo input) {
    return this.mapperFacade.map(input, DistanceInfoDTO.class);
  }

  public Store map(StoreDTO input) {
    return this.mapperFacade.map(input, Store.class);
  }

  public StoreDTO map(Store input) {
    return this.mapperFacade.map(input, StoreDTO.class);
  }
}
