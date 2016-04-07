package com.il.sod.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.entities.ProductType;
import com.il.sod.rest.dto.db.ProductDTO;
import com.il.sod.rest.dto.db.ProductTypeDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum ProductMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ProductMapper() {
		
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("productSetConverter", new ProductSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(ProductDTO.class, Product.class)
			.field("productTypeId", "productType.idProductType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ProductTypeDTO.class, ProductType.class)
			.fieldMap("products", "products").converter("productSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Product map(ProductDTO dto) {
		return this.mapperFacade.map(dto, Product.class);
	}

	public ProductDTO map(Product entity) {
		return this.mapperFacade.map(entity, ProductDTO.class);
	}
	
	public ProductType map(ProductTypeDTO dto) {
		return this.mapperFacade.map(dto, ProductType.class);
	}
	
	public ProductTypeDTO map(ProductType entity) {
		return this.mapperFacade.map(entity, ProductTypeDTO.class);
	}
}

class ProductSetConverter extends BidirectionalConverter<Set<ProductDTO>, Set<Product>> {
	@Override
	public Set<ProductDTO> convertFrom(Set<Product> source, Type<Set<ProductDTO>> arg1) {
		return source.stream()
				.map(item -> ProductMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}

	@Override
	public Set<Product> convertTo(Set<ProductDTO> source, Type<Set<Product>> arg1) {
		return source.stream()
				.map(item -> ProductMapper.INSTANCE.map(item))
				.collect(Collectors.toSet());
	}
}
