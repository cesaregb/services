package com.il.sod.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.entities.ServiceCategory;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.ServiceTypeSpec;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.rest.dto.KeyValue;
import com.il.sod.rest.dto.web.WServiceCategoryDTO;
import com.il.sod.rest.dto.web.WServiceTypeDTO;
import com.il.sod.rest.dto.web.WSpecDTO;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum SpecificObjectsMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;
	
	private ProductRepository productRepository;
	
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	private SpecificObjectsMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("wServiceTypeListConverter", new WServiceTypeListConverter());
		converterFactory.registerConverter("wSpecListConverter", new WSpecListConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("wServiceTypeListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceTypeDTO.class, ServiceType.class)
			.fieldMap("specs", "serviceTypeSpecs").converter("wSpecListConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(WSpecDTO.class, ServiceTypeSpec.class)
			.field("idSpecs","spec.idSpecs")
			.field("description","spec.description")
			.field("name","spec.name")
			.field("optional","spec.optional")
			.field("max_qty","spec.max_qty")
			.byDefault()
			.customize(new CustomMapper<WSpecDTO, ServiceTypeSpec>() {
				
				@Override
				public void mapBtoA(ServiceTypeSpec entity, WSpecDTO dto, MappingContext context) {
					// super.mapBtoA(b, a, context);
					
					Map<Integer, List<KeyValue>> options = new HashMap<Integer, List<KeyValue>>();
					Spec spec = entity.getSpec();
					for (SpecsValue specValue : spec.getSpecsValues()){
						if (options.get(specValue.getSpec().getId()) == null){
							options.put(specValue.getSpec().getId(), new ArrayList<>());
						}
						KeyValue kv = new KeyValue();
						if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
							List<Product> listProduct = productRepository.findByIdProductType(specValue.getIdProductType());
							for (Product p : listProduct){
								kv = new KeyValue();
								kv.setKey(p.getId());
								kv.setValue(p.getName());
								options.get(specValue.getSpec().getId()).add(kv);
							}
							// get all products  by product type....
						}else if (specValue.getType() == Constants.SPEC_TYPE_VALUES){
							kv.setKey(0);
							kv.setValue(specValue.getValue());
							options.get(specValue.getSpec().getId()).add(kv);
						}
					}
					dto.setOptions(options);
				}
			})
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	
	public ServiceCategory map(WServiceCategoryDTO dto) {
		return this.mapperFacade.map(dto, ServiceCategory.class);
	}
	
	public WServiceCategoryDTO map(ServiceCategory entity) {
		return this.mapperFacade.map(entity, WServiceCategoryDTO.class);
	}
	
	public ServiceType map(WServiceTypeDTO dto) {
		return this.mapperFacade.map(dto, ServiceType.class);
	}
	
	public WServiceTypeDTO map(ServiceType entity) {
		return this.mapperFacade.map(entity, WServiceTypeDTO.class);
	}
	
	public ServiceTypeSpec map(WSpecDTO dto) {
		return this.mapperFacade.map(dto, ServiceTypeSpec.class);
	}
	
	public WSpecDTO map(ServiceTypeSpec entity) {
		return this.mapperFacade.map(entity, WSpecDTO.class);
	}
}

class WServiceTypeListConverter extends BidirectionalConverter<List<ServiceType>, List<WServiceTypeDTO>> {
	@Override
	public List<ServiceType> convertFrom(List<WServiceTypeDTO> source, Type<List<ServiceType>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<WServiceTypeDTO> convertTo(List<ServiceType> source, Type<List<WServiceTypeDTO>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
}

class WSpecListConverter extends BidirectionalConverter<List<ServiceTypeSpec>, List<WSpecDTO>> {
	@Override
	public List<ServiceTypeSpec> convertFrom(List<WSpecDTO> source, Type<List<ServiceTypeSpec>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}

	@Override
	public List<WSpecDTO> convertTo(List<ServiceTypeSpec> source, Type<List<WSpecDTO>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toList());
	}
	
}

