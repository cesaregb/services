package com.il.sod.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.AddressRoute;
import com.il.sod.db.model.entities.Route;
import com.il.sod.db.model.entities.Stop;
import com.il.sod.rest.dto.db.AddressRouteDTO;
import com.il.sod.rest.dto.db.RouteDTO;
import com.il.sod.rest.dto.db.StopDTO;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.metadata.Type;

public enum RoutesMapper {

	INSTANCE;
	private final MapperFacade mapperFacade;

	private RoutesMapper() {
		ConverterFactory converterFactory = BaseMapper.MAPPER_FACTORY.getConverterFactory();
		converterFactory.registerConverter("stopsSetConverter", new StopSetConverter());

		BaseMapper.MAPPER_FACTORY.classMap(RouteDTO.class, Route.class)
			.fieldMap("stops", "stops").converter("stopsSetConverter").mapNulls(true).mapNullsInReverse(true).add()
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(AddressRouteDTO.class, AddressRoute.class)
			.field("idStops", "stop.idStops")
			.byDefault()
			.register();
		
		BaseMapper.MAPPER_FACTORY.classMap(StopDTO.class, Stop.class)
			.field("idRoutes", "route.idRoutes")
			.byDefault()
			.register();
		
		mapperFacade = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	}

	public Route map(RouteDTO dto) {
		return this.mapperFacade.map(dto, Route.class);
	}
	
	public RouteDTO map(Route entity) {
		return this.mapperFacade.map(entity, RouteDTO.class);
	}
	
	public Route map(RouteDTO dto, Route entity) {
		this.mapperFacade.map(dto, entity);
		return entity;
	}
	
	public AddressRoute map(AddressRouteDTO dto) {
		return this.mapperFacade.map(dto, AddressRoute.class);
	}
	
	public AddressRouteDTO map(AddressRoute entity) {
		return this.mapperFacade.map(entity, AddressRouteDTO.class);
	}
	
	public AddressRoute map(AddressRouteDTO dto, AddressRoute entity) {
		this.mapperFacade.map(dto, entity);
		return entity;
	}
	
	public Stop map(StopDTO dto) {
		return this.mapperFacade.map(dto, Stop.class);
	}
	
	public StopDTO map(Stop entity) {
		return this.mapperFacade.map(entity, StopDTO.class);
	}
	
	public Stop map(StopDTO dto, Stop entity) {
		this.mapperFacade.map(dto, entity);
		return entity;
	}
}

class StopSetConverter extends BidirectionalConverter<Set<Stop>, Set<StopDTO>> {
	@Override
	public Set<Stop> convertFrom(Set<StopDTO> source, Type<Set<Stop>> destT) {
		return source.stream().map(item -> RoutesMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}

	@Override
	public Set<StopDTO> convertTo(Set<Stop> source, Type<Set<StopDTO>> destT) {
		return source.stream().map(item -> RoutesMapper.INSTANCE.map(item)).collect(Collectors.toSet());
	}
}
