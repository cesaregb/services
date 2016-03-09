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

import com.il.sod.db.model.entities.AssetType;
import com.il.sod.db.model.repositories.AssetTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/asset-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/asset-type", tags = { "asset" })
public class AssetTypeService extends AbstractServiceMutations {
	@Autowired
	AssetTypeRepository assetTypeRepository;

	@PUT
	@ApiOperation(value = "Create Asset Type", response = AssetTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@POST
	@ApiOperation(value = "Update Asset Type", response = AssetTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@DELETE
	@ApiOperation(value = "Create Asset Type", response = AssetTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.deleteEntity(assetTypeRepository, entity.getIdAssetType());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Asset deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Asset Type list", response = AssetTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAssetTypeList() throws SODAPIException {
		List<AssetType> rentityList = this.getEntityList(assetTypeRepository);
		List<AssetTypeDTO> list = rentityList.stream().map((i) -> {
			AssetTypeDTO dto = AssetMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
