package com.il.sod.mapper;

import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.entities.ServiceProduct;
import com.il.sod.rest.dto.db.ServiceProductDTO;
import com.il.sod.rest.dto.db.ProductDTO;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.ConverterFactory;

public enum ProductMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private ProductMapper() {

		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("productSetConverter", new ProductSetConverter());
		converterFactory.registerConverter("serviceTypeSet2IntConverter", new ServiceTypeSet2IntConverter());

		BaseMapper.MAPPER_FACTORY.classMap(ProductDTO.class, Product.class)
			.field("idProductType", "productType.idProductType")
			.field("typeName", "productType.name")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(ProductTypeDTO.class, ProductType.class)
			.fieldMap("products", "products").converter("productSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("serviceTypes", "serviceTypes").converter("serviceTypeSet2IntConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();

		BaseMapper.MAPPER_FACTORY.classMap(ServiceProductDTO.class, ServiceProduct.class)
			.field("idService", "service.idService")
			.byDefault()
			.register();


		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Product map(ProductDTO dto) {
		return this.mapperFacade.map(dto, Product.class);
	}
	
	public ProductDTO map(Product dto) {
		return this.mapperFacade.map(dto, ProductDTO.class);
	}

	public ProductType map(ProductTypeDTO dto) {
		return this.mapperFacade.map(dto, ProductType.class);
	}

	public ProductTypeDTO map(ProductType dto) {
		return this.mapperFacade.map(dto, ProductTypeDTO.class);
	}

	public ServiceProduct map(ServiceProductDTO dto) {
		return this.mapperFacade.map(dto, ServiceProduct.class);
	}

	public ServiceProductDTO map(ServiceProduct dto) {
		return this.mapperFacade.map(dto, ServiceProductDTO.class);
	}

}



