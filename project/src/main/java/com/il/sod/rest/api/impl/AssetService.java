package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Asset;
import com.il.sod.db.model.repositories.AssetRepository;
import com.il.sod.db.model.repositories.AssetTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/asset")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/asset", tags = { "asset" })
public class AssetService extends AbstractServiceMutations {
	@Autowired
	AssetRepository assetRepository;

	@Autowired
	AssetTypeRepository assetTypeRepository;

	@POST
	@ApiOperation(value = "Create Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAssetById(@PathParam("id") String id, AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteClient(@PathParam("id") String id) throws SODAPIException {
		Asset entity = assetRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(assetRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Asset list", response = AssetDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAssetList() throws SODAPIException {
		List<Asset> entityList = this.getEntityList(assetRepository);
		List<AssetDTO> list = entityList.stream().map((i) -> {
			AssetDTO dto = AssetMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
