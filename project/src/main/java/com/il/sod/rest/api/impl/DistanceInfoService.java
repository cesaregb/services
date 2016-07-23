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

import com.il.sod.db.model.entities.DistanceInfo;
import com.il.sod.db.model.repositories.DistanceInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.DistanceInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/distance-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/distance-info", tags = { "app-utils" })
public class DistanceInfoService extends AbstractServiceMutations {
	@Autowired
	DistanceInfoRepository distanceInfoRepository;

	@POST
	@ApiOperation(value = "Create Service Type", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		try {
			DistanceInfo entity = converter.map(dto, DistanceInfo.class);
			this.saveEntity(distanceInfoRepository, entity);
			dto = converter.map(entity, DistanceInfoDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Service Type", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		try {
			DistanceInfo entity = converter.map(dto, DistanceInfo.class);
			this.updateEntity(distanceInfoRepository, entity);
			dto = converter.map(entity, DistanceInfoDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Service Type", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateDistanceInfoById(@PathParam("id") String id, DistanceInfoDTO dto) throws SODAPIException {
		try {
			DistanceInfo entity = converter.map(dto, DistanceInfo.class);
			this.updateEntity(distanceInfoRepository, entity);
			dto = converter.map(entity, DistanceInfoDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		DistanceInfo entity = distanceInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(distanceInfoRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = DistanceInfoDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getDistanceInfoList() throws SODAPIException {
//		List<DistanceInfo> rentityList = this.getEntityList(distanceInfoRepository);
		List<DistanceInfo> rentityList = distanceInfoRepository.findAllOrderByDistance();
		List<DistanceInfoDTO> list = rentityList.stream().map((i) -> {
			DistanceInfoDTO dto = converter.map(i, DistanceInfoDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
