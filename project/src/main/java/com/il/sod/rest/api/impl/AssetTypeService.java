package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.AssetType;
import com.il.sod.db.model.repositories.AssetTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetTypeDTO;
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
@Path("/asset-type")
@Produces(MediaType.APPLICATION_JSON)
//@Api(value = "/asset-type", tags = { "asset" })
public class AssetTypeService extends AbstractServiceMutations {
	@Autowired
	AssetTypeRepository assetTypeRepository;

	@POST
	@ApiOperation(value = "Create Asset Type", response = AssetTypeDTO.class)
	public Response saveAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Asset Type", response = AssetTypeDTO.class)
	public Response updateAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		AssetType entity = assetTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(assetTypeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Asset Type list", response = AssetTypeDTO.class, responseContainer = "List")
	public Response getAssetTypeList() throws SODAPIException {
		List<AssetType> rentityList = this.getEntityList(assetTypeRepository);
		List<AssetTypeDTO> list = rentityList.stream().map((i) -> {
			AssetTypeDTO dto = AssetMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
