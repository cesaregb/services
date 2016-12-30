package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Asset;
import com.il.sod.db.model.repositories.AssetRepository;
import com.il.sod.db.model.repositories.AssetTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
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
@Path("/asset")
@Produces(MediaType.APPLICATION_JSON)
//@Api(value = "/asset", tags = { "asset" })
public class AssetService extends AbstractServiceMutations {
	@Autowired
	AssetRepository assetRepository;

	@Autowired
	AssetTypeRepository assetTypeRepository;

	@POST
	@ApiOperation(value = "Create Asset", response = AssetDTO.class)
	public Response saveAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Asset", response = AssetDTO.class)
	public Response updateAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}


	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Asset entity = assetRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(assetRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Asset list", response = AssetDTO.class, responseContainer = "List")
	public Response getAssetList() throws SODAPIException {
		List<Asset> entityList = this.getEntityList(assetRepository);
		List<AssetDTO> list = entityList.stream().map((i) -> {
			AssetDTO dto = AssetMapper.INSTANCE.map(i);
			return dto;
		})
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
