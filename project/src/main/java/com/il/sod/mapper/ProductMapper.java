package com.il.sod.mapper;

import java.util.List;
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
		converterFactory.registerConverter("productListConverter", new ProductListConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(ProductDTO.class, Product.class)
			.field("productTypeId", "productType.idProductType")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ProductTypeDTO.class, ProductType.class)
			.fieldMap("products", "products").converter("productListConverter").mapNulls(true).mapNullsInReverse(true).add()
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

class ProductListConverter extends BidirectionalConverter<List<ProductDTO>, List<Product>> {
	@Override
	public List<ProductDTO> convertFrom(List<Product> source, Type<List<ProductDTO>> arg1) {
		return source.stream()
				.map(item -> ProductMapper.INSTANCE.map(item))
				.collect(Collectors.toList());
	}

	@Override
	public List<Product> convertTo(List<ProductDTO> source, Type<List<Product>> arg1) {
		return source.stream()
				.map(item -> ProductMapper.INSTANCE.map(item))
				.collect(Collectors.toList());
	}
}
