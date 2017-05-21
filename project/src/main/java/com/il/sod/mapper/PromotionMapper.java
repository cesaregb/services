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

		BaseMapper.MAPPER_FACTORY.classMap(PromotionDTO.class, PriceAdjustment.class)
			.field("idPromotionType", "promotionType.idPromotionType")
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(PromotionTypeDTO.class, PriceAdjustmentType.class)
			.fieldMap("promotions", "promotions").converter("promotionSetConverter").mapNulls(false).mapNullsInReverse(true).add()
			.byDefault()
			.register();


		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public PriceAdjustment map(PromotionDTO input) {
		return this.mapperFacade.map(input, PriceAdjustment.class);
	}

	public PromotionDTO map(PriceAdjustment input) {
		return this.mapperFacade.map(input, PromotionDTO.class);
	}

	public PriceAdjustmentType map(PromotionTypeDTO dto) {
		return this.mapperFacade.map(dto, PriceAdjustmentType.class);
	}

	public PromotionTypeDTO map(PriceAdjustmentType dto) {
		return this.mapperFacade.map(dto, PromotionTypeDTO.class);
	}

}



