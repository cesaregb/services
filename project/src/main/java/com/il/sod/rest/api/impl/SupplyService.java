package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Supply;
import com.il.sod.db.model.repositories.SupplyRepository;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SupplyMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SupplyDTO;
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
@Path("/supplies")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/supplies", tags = { "supplies" })
public class SupplyService extends AbstractServiceMutations {
	@Autowired
	SupplyRepository supplyRepository;

	@Autowired
	SupplyTypeRepository supplyTypeRepository;

	@POST
	@ApiOperation(value = "Create Supply", response = SupplyDTO.class)
	public Response saveSupply(SupplyDTO dto) throws SODAPIException {

			Supply entity = SupplyMapper.INSTANCE.map(dto);
			this.saveEntity(supplyRepository, entity);
			dto = SupplyMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Supply", response = SupplyDTO.class)
	public Response updateSupply(SupplyDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SupplyDTO dto) throws SODAPIException {

			Supply entity = SupplyMapper.INSTANCE.map(dto);
			this.updateEntity(supplyRepository, entity);
			dto = SupplyMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Supply entity = supplyRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(supplyRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Supply list", response = SupplyDTO.class, responseContainer = "List")
	public Response getSupplyList() throws SODAPIException {
		List<Supply> entityList = this.getEntityList(supplyRepository);
		List<SupplyDTO> list = entityList.stream().map((i) -> {
			SupplyDTO dto = SupplyMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
