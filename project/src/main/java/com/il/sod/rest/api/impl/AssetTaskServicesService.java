package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.AssetTaskService;
import com.il.sod.db.model.repositories.AssetTaskServiceRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetTaskServiceDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/asset-task-service")
@Produces(MediaType.APPLICATION_JSON)
//@Api(value = "/asset-task-service", tags = { "service" })
public class AssetTaskServicesService extends AbstractServiceMutations {
	@Autowired
	AssetTaskServiceRepository assetTaskServiceRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = AssetTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAssetTaskService(AssetTaskServiceDTO dto) throws SODAPIException {
		try {
			AssetTaskService entity = converter.map(dto, AssetTaskService.class);
			this.saveEntity(assetTaskServiceRepository, entity);
			dto = converter.map(entity, AssetTaskServiceDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = AssetTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAssetTaskService(AssetTaskServiceDTO dto) throws SODAPIException {
		try {
			AssetTaskService entity = converter.map(dto, AssetTaskService.class);
			this.updateEntity(assetTaskServiceRepository, entity);
			dto = converter.map(entity, AssetTaskServiceDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = AssetTaskServiceDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAssetTaskService(AssetTaskServiceDTO dto) throws SODAPIException {
		try {
			AssetTaskService entity = converter.map(dto, AssetTaskService.class);
			this.deleteEntity(assetTaskServiceRepository, entity.getIdAssetTaskService());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = AssetTaskServiceDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAssetTaskServiceList() throws SODAPIException {
		List<AssetTaskService> rentityList = this.getEntityList(assetTaskServiceRepository);
		List<AssetTaskServiceDTO> list = rentityList.stream().map((i) -> {
			AssetTaskServiceDTO dto = converter.map(i, AssetTaskServiceDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
