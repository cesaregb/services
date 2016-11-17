package com.il.sod.mapper;

import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.*;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum PromotionMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private PromotionMapper() {

		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("promotionSetConverter", new PromotionSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(PromotionDTO.class, Promotion.class)
			.field("idPromotionType", "promotionType.idPromotionType")
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(PromotionTypeDTO.class, PromotionType.class)
			.fieldMap("promotions", "promotions").converter("promotionSetConverter").mapNulls(false).mapNullsInReverse(true).add()
			.byDefault()
			.register();


		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Promotion map(PromotionDTO input) {
		return this.mapperFacade.map(input, Promotion.class);
	}

	public PromotionDTO map(Promotion input) {
		return this.mapperFacade.map(input, PromotionDTO.class);
	}

	public PromotionType map(PromotionTypeDTO dto) {
		return this.mapperFacade.map(dto, PromotionType.class);
	}

	public PromotionTypeDTO map(PromotionType dto) {
		return this.mapperFacade.map(dto, PromotionTypeDTO.class);
	}

}



