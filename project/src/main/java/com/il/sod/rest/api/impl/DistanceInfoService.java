package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.DistanceInfo;
import com.il.sod.db.model.repositories.DistanceInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.StoreInfoMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.DistanceInfoDTO;
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
@Path("/distance-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/distance-info", tags = { "app-utils" })
public class DistanceInfoService extends AbstractServiceMutations {

	@Autowired
	DistanceInfoRepository distanceInfoRepository;

	@POST
	@ApiOperation(value = "Create Distance Info", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		try {
			DistanceInfo entity = StoreInfoMapper.INSTANCE.map(dto);
			this.saveEntity(distanceInfoRepository, entity);
			dto = StoreInfoMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Distance Info", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(DistanceInfoDTO dto) throws SODAPIException {
		try {
			DistanceInfo entity = StoreInfoMapper.INSTANCE.map(dto);
			this.updateEntity(distanceInfoRepository, entity);
			dto = StoreInfoMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Distance Info", response = DistanceInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateDistanceInfoById(@PathParam("id") String id, DistanceInfoDTO dto) throws SODAPIException {
		return updateEntity(dto);
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
	@ApiOperation(value = "Get Distance Info list", response = DistanceInfoDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getDistanceInfoList() throws SODAPIException {
		List<DistanceInfo> rentityList = distanceInfoRepository.findAllOrderByDistance();
		List<DistanceInfoDTO> list = rentityList.stream().map((i) -> {
			DistanceInfoDTO dto = StoreInfoMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
