package com.il.sod.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Asset;
import com.il.sod.db.model.entities.AssetType;
import com.il.sod.rest.dto.db.AssetDTO;
import com.il.sod.rest.dto.db.AssetTypeDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum AssetMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private AssetMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("assetSetConverter", new AssetSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(AssetDTO.class, Asset.class)
			.field("idAssetType", "assetType.idAssetType")
			.field("assetTypeName", "assetType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AssetTypeDTO.class, AssetType.class)
			.fieldMap("assets", "assets").converter("assetSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Asset map(AssetDTO dto) {
		return this.mapperFacade.map(dto, Asset.class);
	}

	public AssetDTO map(Asset entity) {
		return this.mapperFacade.map(entity, AssetDTO.class);
	}
	
	public AssetType map(AssetTypeDTO dto) {
		return this.mapperFacade.map(dto, AssetType.class);
	}
	
	public AssetTypeDTO map(AssetType entity) {
		return this.mapperFacade.map(entity, AssetTypeDTO.class);
	}
}

class AssetSetConverter extends BidirectionalConverter<Set<AssetDTO>, Set<Asset>> {
	@Override
	public Set<AssetDTO> convertFrom(Set<Asset> source, Type<Set<AssetDTO>> arg1) {
		return source.stream()
				.map(item -> AssetMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Asset> convertTo(Set<AssetDTO> source, Type<Set<Asset>> arg1) {
		return source.stream()
				.map(item -> AssetMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}
}
