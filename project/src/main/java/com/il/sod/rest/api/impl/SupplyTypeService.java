package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.SupplyType;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SupplyMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SupplyTypeDTO;
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
@Path("/supplies/supply-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/supplies/supply-type", tags = { "supplies" })
public class SupplyTypeService extends AbstractServiceMutations {

	@Autowired
	SupplyTypeRepository supplyTypeRepository;

	@POST
	@ApiOperation(value = "Create Supply Type", response = SupplyTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSupplyType(SupplyTypeDTO dto) throws SODAPIException {
		try {
			SupplyType entity = SupplyMapper.INSTANCE.map(dto);
			this.saveEntity(supplyTypeRepository, entity);
			dto = SupplyMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Supply Type", response = SupplyTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSupplyType(SupplyTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SupplyTypeDTO dto) throws SODAPIException {
		try {
			SupplyType entity = SupplyMapper.INSTANCE.map(dto);
			this.updateEntity(supplyTypeRepository, entity);
			dto = SupplyMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Supply Type", response = SupplyTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSupplyTypeById(@PathParam("id") String id, SupplyTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}
	
	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		SupplyType entity = supplyTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(supplyTypeRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Supply Type list", response = SupplyTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSupplyTypeList() throws SODAPIException {
		List<SupplyType> rentityList = this.getEntityList(supplyTypeRepository);
		List<SupplyTypeDTO> list = rentityList.stream().map((i) -> {
			SupplyTypeDTO dto = SupplyMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
