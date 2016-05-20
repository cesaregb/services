package com.il.sod.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
		converterFactory.registerConverter("wServiceTypeSetConverter", new WServiceTypeSetConverter());
		converterFactory.registerConverter("wSpecSetConverter", new WSpecSetConverter());
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("wServiceTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceTypeDTO.class, ServiceType.class)
			.fieldMap("specs", "serviceTypeSpecs").converter("wSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
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
					Map<Integer, List<KeyValue<Integer, String>>> options = new HashMap<Integer, List<KeyValue<Integer, String>>>();
					Spec spec = entity.getSpec();
					for (SpecsValue specValue : spec.getSpecsValues()){
						if (options.get(specValue.getSpec().getId()) == null){
							options.put(specValue.getSpec().getId(), new ArrayList<>());
						}
						KeyValue<Integer, String> kv = new KeyValue<Integer, String>();
						if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
							// get all products  by product type....
							List<Product> listProduct = productRepository.findByIdProductType(specValue.getIdProductType());
							for (Product p : listProduct){
								kv = new KeyValue<Integer, String>();
								kv.setKey(p.getId());
								kv.setValue(p.getName());
								options.get(specValue.getSpec().getId()).add(kv);
							}
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

class WServiceTypeSetConverter extends BidirectionalConverter<Set<ServiceType>, Set<WServiceTypeDTO>> {
	@Override
	public Set<ServiceType> convertFrom(Set<WServiceTypeDTO> source, Type<Set<ServiceType>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<WServiceTypeDTO> convertTo(Set<ServiceType> source, Type<Set<WServiceTypeDTO>> arg1) {
		System.out.println("****source: " + source.size());
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}

class WSpecSetConverter extends BidirectionalConverter<Set<ServiceTypeSpec>, Set<WSpecDTO>> {
	@Override
	public Set<ServiceTypeSpec> convertFrom(Set<WSpecDTO> source, Type<Set<ServiceTypeSpec>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<WSpecDTO> convertTo(Set<ServiceTypeSpec> source, Type<Set<WSpecDTO>> arg1) {
		return source.stream().map(item -> SpecificObjectsMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
	
}

