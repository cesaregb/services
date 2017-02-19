package com.il.sod.converter.services;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.SupplyRepository;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.dto.KeyValueSpecs;
import com.il.sod.rest.dto.db.MenuDTO;
import com.il.sod.rest.dto.db.ProductDTO;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import com.il.sod.rest.dto.serve.WServiceTypeDTO;
import com.il.sod.rest.dto.serve.WSpecDTO;
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
	SupplyRepository supplyRepository;

	private final MapperFacade mapperFacade;
	
	public SpecificObjectsConverterService() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("wServiceTypeSetConverter", new WServiceTypeSetConverter());
		converterFactory.registerConverter("wSpecSetConverter", new WSpecSetConverter());
		converterFactory.registerConverter("productTepe2ProductSetConverter", new ProductTepe2ProductSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(WServiceCategoryDTO.class, ServiceCategory.class)
			.fieldMap("serviceTypes", "serviceTypes").converter("wServiceTypeSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(WServiceTypeDTO.class, ServiceType.class)
			.fieldMap("specs", "specs").converter("wSpecSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.fieldMap("products", "productTypes").converter("productTepe2ProductSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
			
		BaseMapper.MAPPER_FACTORY.classMap(WSpecDTO.class, Spec.class)
			.byDefault()
			.customize(new CustomMapper<WSpecDTO, Spec>() {
				
				@Override
				public void mapBtoA(Spec spec, WSpecDTO dto, MappingContext context) {
					Map<Integer, List<KeyValueSpecs<Integer, String>>> options = new HashMap<Integer, List<KeyValueSpecs<Integer, String>>>();
					for (SpecsValue specValue : spec.getSpecsValues()){
						options.putIfAbsent(specValue.getSpec().getId(), new ArrayList<>());
						KeyValueSpecs<Integer, String> kv = new KeyValueSpecs<Integer, String>();
						if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
							// get all supplies  by supply type....
							List<Supply> listSupply = supplyRepository.findByIdSupplyType(specValue.getIdSupplyType());
							for (Supply p : listSupply){
								kv = new KeyValueSpecs<>();
								kv.setKey(p.getId());
								kv.setValue(p.getName());
								kv.setServiceIncrement(0d);
								kv.setSpecPrice(p.getServiceIncrement());
								kv.setCostType(1); // 0 = increment; 1 = price
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
	
	public Spec map(WSpecDTO dto) {
		return this.mapperFacade.map(dto, Spec.class);
	}
	
	public WSpecDTO map(Spec entity) {
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

class WSpecSetConverter extends BidirectionalConverter<Set<Spec>, Set<WSpecDTO>> {
	@Override
	public Set<Spec> convertFrom(Set<WSpecDTO> source, Type<Set<Spec>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<WSpecDTO> convertTo(Set<Spec> source, Type<Set<WSpecDTO>> arg1) {
		return source.stream().map(item -> (new SpecificObjectsConverterService()).map(item)).collect(Collectors.toSet());
	}
	
}

class ProductTepe2ProductSetConverter extends BidirectionalConverter<Set<ProductDTO>, Set<ProductType>> {

	@Override
	public Set<ProductType> convertTo(Set<ProductDTO> source, Type<Set<ProductType>> type) {
		// never used... not implemented..
		return null;
	}

	@Override
	public Set<ProductDTO> convertFrom(Set<ProductType> source, Type<Set<ProductDTO>> type) {
		Set<ProductDTO> result = new HashSet<>();
		source.forEach(i -> {
			i.getProducts().forEach(e -> result.add(ProductMapper.INSTANCE.map(e)));
		});

		return result;
	}
}
