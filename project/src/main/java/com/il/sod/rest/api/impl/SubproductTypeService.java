package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.SubproductType;
import com.il.sod.db.model.repositories.SubproductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SubproductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SubproductTypeDTO;
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
@Path("/subproductType")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/subproductType", tags = { "subproduct" })
public class SubproductTypeService extends AbstractServiceMutations {

	@Autowired
	SubproductTypeRepository subproductTypeRepository;

	@POST
	@ApiOperation(value = "Create Subproduct Type", response = SubproductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveSubproductType(SubproductTypeDTO dto) throws SODAPIException {
		try {
			SubproductType entity = SubproductMapper.INSTANCE.map(dto);
			this.saveEntity(subproductTypeRepository, entity);
			dto = SubproductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Subproduct Type", response = SubproductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSubproductType(SubproductTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Subproduct Type", response = SubproductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateSubproductTypeById(@PathParam("id") String id, SubproductTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SubproductTypeDTO dto) throws SODAPIException {
		try {
			SubproductType entity = SubproductMapper.INSTANCE.map(dto);
			this.updateEntity(subproductTypeRepository, entity);
			dto = SubproductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Subproduct Type", response = SubproductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteSubproductType(@PathParam("id") String id, SubproductTypeDTO dto) throws SODAPIException {
		try {
			SubproductType entity = subproductTypeRepository.findOne(Integer.valueOf(id));
			if (entity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not found");
			}
			this.softDeleteEntity(subproductTypeRepository, entity.getId());
			return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Subproduct Type list", response = SubproductTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getSubproductTypeList() throws SODAPIException {
		List<SubproductType> rentityList = this.getEntityList(subproductTypeRepository);
		List<SubproductTypeDTO> list = rentityList.stream().map((i) -> {
			SubproductTypeDTO dto = SubproductMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
