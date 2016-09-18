package com.il.sod.converter.services;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.mapper.SubproductMapper;
import com.il.sod.rest.dto.KeyValueSpecs;
import com.il.sod.rest.dto.db.MenuDTO;
import com.il.sod.rest.dto.db.SubproductDTO;
import com.il.sod.rest.dto.specifics.WServiceCategoryDTO;
import com.il.sod.rest.dto.specifics.WServiceTypeDTO;
import com.il.sod.rest.dto.specifics.WSpecDTO;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SpecificObjectsConverterService {
	
	@Autowired
	ProductRepository productRepository;
	
	private final MapperFacade mapperFacade;
	
	public SpecificObjectsConverterService() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("wServiceTypeSetConverter", new WServiceTypeSetConverter());
		converterFactory.registerConverter("wSpecSetConverter", new WSpecSetConverter());
		converterFactory.registerConverter("subproductTepe2SubproductSetConverter", new SubproductTepe2SubproductSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(WServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("wServiceTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceTypeDTO.class, ServiceType.class)
			.fieldMap("specs", "serviceTypeSpecs").converter("wSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("subproducts", "subproductTypes").converter("subproductTepe2SubproductSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
			
		BaseMapper.MAPPER_FACTORY.classMap(WSpecDTO.class, ServiceTypeSpec.class)
			.field("idSpecs","spec.idSpecs")
			.field("description","spec.description")
			.field("name","spec.name")
			.field("optional","spec.optional")
			.field("max_qty","spec.max_qty")
			.field("primarySpec","spec.primarySpec")
			.byDefault()
			.customize(new CustomMapper<WSpecDTO, ServiceTypeSpec>() {
				
				@Override
				public void mapBtoA(ServiceTypeSpec entity, WSpecDTO dto, MappingContext context) {
					Map<Integer, List<KeyValueSpecs<Integer, String>>> options = new HashMap<Integer, List<KeyValueSpecs<Integer, String>>>();
					Spec spec = entity.getSpec();
					for (SpecsValue specValue : spec.getSpecsValues()){
						options.putIfAbsent(specValue.getSpec().getId(), new ArrayList<>());
						KeyValueSpecs<Integer, String> kv = new KeyValueSpecs<Integer, String>();
						if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
							// get all products  by product type....
							List<Product> listProduct = productRepository.findByIdProductType(specValue.getIdProductType());
							for (Product p : listProduct){
								kv = new KeyValueSpecs<Integer, String>();
								kv.setKey(p.getId());
								kv.setValue(p.getName());
								kv.setServiceIncrement(p.getServiceIncrement());
								kv.setSpecPrice(0d); // if we come from product we dont assign price to a product... 
								kv.setCostType(0);
								options.get(specValue.getSpec().getId()).add(kv);
							}
						}else if (specValue.getType() == Constants.SPEC_TYPE_VALUES){
							kv.setKey(specValue.getId());
							kv.setValue(specValue.getValue());
							kv.setServiceIncrement(specValue.getServiceIncrement());
							kv.setSpecPrice(specValue.getSpecPrice());
							kv.setCostType(specValue.getCostType());
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
	
	public MenuDTO map(Menu entity){
		return this.mapperFacade.map(entity, MenuDTO.class);
	}
	
	public Menu map(MenuDTO dto){
		return this.mapperFacade.map(dto, Menu.class);
	}
}

class WServiceTypeSetConverter extends BidirectionalConverter<Set<ServiceType>, Set<WServiceTypeDTO>> {
	@Override
	public Set<ServiceType> convertFrom(Set<WServiceTypeDTO> source, Type<Set<ServiceType>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<WServiceTypeDTO> convertTo(Set<ServiceType> source, Type<Set<WServiceTypeDTO>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}
}

class WSpecSetConverter extends BidirectionalConverter<Set<ServiceTypeSpec>, Set<WSpecDTO>> {
	@Override
	public Set<ServiceTypeSpec> convertFrom(Set<WSpecDTO> source, Type<Set<ServiceTypeSpec>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<WSpecDTO> convertTo(Set<ServiceTypeSpec> source, Type<Set<WSpecDTO>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}
	
}

class SubproductTepe2SubproductSetConverter extends BidirectionalConverter<Set<SubproductDTO>, Set<SubproductType>> {

	@Override
	public Set<SubproductType> convertTo(Set<SubproductDTO> source, Type<Set<SubproductType>> type) {
		// never used... not implemented..
		return null;
	}

	@Override
	public Set<SubproductDTO> convertFrom(Set<SubproductType> source, Type<Set<SubproductDTO>> type) {
		Set<SubproductDTO> result = new HashSet<>();
		source.forEach(i -> {
			i.getSubproducts().forEach(e -> result.add(SubproductMapper.INSTANCE.map(e)));
		});

		return result;
	}
}
