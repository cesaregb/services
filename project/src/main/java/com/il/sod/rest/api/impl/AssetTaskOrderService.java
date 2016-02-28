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

import com.il.sod.db.model.entities.AssetTaskOrder;
import com.il.sod.db.model.repositories.AssetTaskOrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetTaskOrderDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/asset-task-order")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/asset-task-order", tags = { "order" })
public class AssetTaskOrderService extends AbstractServiceMutations {
	@Autowired
	AssetTaskOrderRepository assetTaskOrderRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = AssetTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveAssetTaskOrder(AssetTaskOrderDTO dto) throws SODAPIException {
		try {
			AssetTaskOrder entity = converter.map(dto, AssetTaskOrder.class);
			this.saveEntity(assetTaskOrderRepository, entity);
			dto = converter.map(entity, AssetTaskOrderDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = AssetTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateAssetTaskOrder(AssetTaskOrderDTO dto) throws SODAPIException {
		try {
			AssetTaskOrder entity = converter.map(dto, AssetTaskOrder.class);
			this.updateEntity(assetTaskOrderRepository, entity);
			dto = converter.map(entity, AssetTaskOrderDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = AssetTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteAssetTaskOrder(AssetTaskOrderDTO dto) throws SODAPIException {
		try {
			AssetTaskOrder entity = converter.map(dto, AssetTaskOrder.class);
			this.deleteEntity(assetTaskOrderRepository, entity.getIdAssetTaskOrder());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = AssetTaskOrderDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getAssetTaskOrderList() throws SODAPIException {
		List<AssetTaskOrder> rentityList = this.getEntityList(assetTaskOrderRepository);
		List<AssetTaskOrderDTO> list = rentityList.stream().map((i) -> {
			AssetTaskOrderDTO dto = converter.map(i, AssetTaskOrderDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
