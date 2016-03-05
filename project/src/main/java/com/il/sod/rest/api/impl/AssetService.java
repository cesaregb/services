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
//@Api(value = "/asset", tags = { "generic" })
public class AssetService extends AbstractServiceMutations {
	@Autowired
	AssetRepository assetRepository;

	@Autowired
	AssetTypeRepository assetTypeRepository;

	@PUT
	@ApiOperation(value = "Create Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAsset(AssetDTO dto) throws SODAPIException {
		try {
			Asset entity = AssetMapper.INSTANCE.map(dto);
			this.saveEntity(assetRepository, entity);
			dto = AssetMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAsset(AssetDTO dto) throws SODAPIException {
		try {
			Asset entity = AssetMapper.INSTANCE.map(dto);
			this.updateEntity(assetRepository, entity);
			dto = AssetMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

//	private AssetType getAssetType(Asset entity, int idAssetType) {
//		entity.setAssetType(getAssetType(entity, dto.getAssetTypeId()));
//		AssetType tt = this.getEntity(assetTypeRepository, idAssetType);
//		tt.addAsset(entity);
//		return tt;
//	}


	@DELETE
	@ApiOperation(value = "Create Asset", response = AssetDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAsset(AssetDTO dto) throws SODAPIException {
		try {
			Asset entity = AssetMapper.INSTANCE.map(dto);
			this.deleteEntity(assetRepository, entity.getIdAsset());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Asset deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
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
