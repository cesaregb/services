package com.il.sod.rest.api.impl;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.AddressRoute;
import com.il.sod.db.model.entities.Route;
import com.il.sod.db.model.entities.Stop;
import com.il.sod.db.model.repositories.AddressRepository;
import com.il.sod.db.model.repositories.AddressRouteRepository;
import com.il.sod.db.model.repositories.RoutesRepository;
import com.il.sod.db.model.repositories.StopRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.mapper.RoutesMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AddressDTO;
import com.il.sod.rest.dto.db.AddressGenericDTO;
import com.il.sod.rest.dto.db.AddressRouteDTO;
import com.il.sod.rest.dto.db.StopDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/stops")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/stops", tags = { "routes" })
public class StopsService extends AbstractServiceMutations {

	@Autowired
	StopRepository stopRepository;

	@Autowired
	AddressRouteRepository addressRouteRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	RoutesRepository routesRepository;

	@POST
	@ApiOperation(value = "Create Stop", response = StopDTO.class)
	public Response saveStop(StopDTO dto) throws SODAPIException {

		if ( dto.getType() == Constants.ADDRESS_ROUTE_TYPE ){
			AddressRoute addressEntity = RoutesMapper.INSTANCE.map(dto.getAddress());
			this.saveEntity(addressRouteRepository, addressEntity);
			dto.setIdAddress(addressEntity.getIdAddressRoute());
		}

		Stop entity = RoutesMapper.INSTANCE.map(dto);
		assignDependencyToChilds(entity);
		this.saveEntity(stopRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Stop", response = StopDTO.class)
	public Response updateStop(StopDTO dto) throws SODAPIException {
		Stop entity = this.getEntity(stopRepository, dto.getIdStops());

		if ( dto.getType() == Constants.ADDRESS_ROUTE_TYPE ){
			AddressRoute addressEntity = this.getEntity(addressRouteRepository, Integer.valueOf(dto.getIdAddress()));
			if(addressEntity != null){
				addressEntity = RoutesMapper.INSTANCE.map(dto.getAddress(), addressEntity);
			}else{
				addressEntity = RoutesMapper.INSTANCE.map(dto.getAddress());
			}
			this.saveEntity(addressRouteRepository, addressEntity);
			entity.setIdAddress(addressEntity.getId());
		}

		this.updateEntity(stopRepository, entity);
		dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Stop", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
		Stop entity = stopRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Stop not found");
		}
		Route route = entity.getRoute();
		route.removeStop(entity);
		this.saveEntity(routesRepository, route);
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Stop deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Stop list", response = StopDTO.class, responseContainer = "List")
	public Response getStopList() throws SODAPIException {
		List<Stop> rentityList = this.getEntityList(stopRepository);
		List<StopDTO> list = rentityList.stream().map((i) -> {
			StopDTO dto = RoutesMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);

	}

	@GET
	@Path("/byId/{stopId}")
	@ApiOperation(value = "Get Stop by id", response = StopDTO.class)
	public Response getStopById(@PathParam("stopId") String stopId) throws SODAPIException {
		Stop entity = this.getEntity(stopRepository, Integer.valueOf(stopId));
		StopDTO dto = RoutesMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto);
	}

	@GET
	@Path("/address/{type}/{id}")
	@ApiOperation(value = "Get Stop by id", response = AddressGenericDTO.class)
	public Response getById(@PathParam("type") String type, @PathParam("id") String id) throws SODAPIException {
		if (Integer.valueOf(type) == Constants.ADDRESS_ROUTE_TYPE){
			AddressRoute entity = this.getEntity(addressRouteRepository, Integer.valueOf(id));
			AddressRouteDTO dto = RoutesMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto);
		}else if (Integer.valueOf(type) == Constants.ADDRESS_CLIENT_TYPE){
			Address entity = this.getEntity(addressRepository, Integer.valueOf(id));
			AddressDTO dto = ClientMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto);
		}
		throw new SODAPIException(Response.Status.BAD_REQUEST, "Not valid type");
	}

	private void assignDependencyToChilds(Stop entity) {
		if (entity.getRoute() != null){
			entity.getRoute().addStop(entity);
		}
	}
}
