package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Store;
import com.il.sod.db.model.repositories.StoreRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.StoreInfoMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.StoreDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Path("/stores")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/stores", tags = {"app-utils"})
public class StoreService extends AbstractServiceMutations {

	@Autowired
	StoreRepository storeRepository;

	@POST
	@ApiOperation(value = "Create Store", response = StoreDTO.class)
	public Response saveStore(StoreDTO dto) throws SODAPIException {
		Store entity = StoreInfoMapper.INSTANCE.map(dto);
		this.saveEntity(storeRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Store", response = StoreDTO.class)
	public Response updateStore(StoreDTO dto) throws SODAPIException {
		Store entity = StoreInfoMapper.INSTANCE.map(dto);
		this.updateEntity(storeRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Store entity = storeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(storeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Store list", response = StoreDTO.class, responseContainer = "List")
	public Response getStoreList() throws SODAPIException {
		List<Store> rentityList = storeRepository.findAll();
		List<StoreDTO> list = rentityList.stream().map((i) -> {
			StoreDTO dto = StoreInfoMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byId/{id}")
	@ApiOperation(value = "Get Store by ID", response = StoreDTO.class)
	public Response getStoreById(@PathParam("id") Integer id) throws SODAPIException {
		Store entity = storeRepository.findOne(id);
		StoreDTO result = StoreInfoMapper.INSTANCE.map(entity);
		result.getDistanceInfos();
		return castEntityAsResponse(result);
	}

}
