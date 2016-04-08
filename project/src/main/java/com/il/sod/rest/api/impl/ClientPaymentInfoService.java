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

import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.repositories.ClientPaymentInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PaymentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/client-payment-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/client-payment-info", tags = { "payment" })
public class ClientPaymentInfoService extends AbstractServiceMutations {

	@Autowired
	ClientPaymentInfoRepository clientPaymentInfoRepository;

	@POST
	@ApiOperation(value = "Create Payment Info", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.saveEntity(clientPaymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Payment Info", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.updateEntity(clientPaymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Payment Info", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		try {
			ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.deleteEntity(clientPaymentInfoRepository, entity.getIdClientPaymentInfo());
			return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Payment Info deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getClientPaymentInfoList() throws SODAPIException {
		List<ClientPaymentInfo> rentityList = this.getEntityList(clientPaymentInfoRepository);
		List<ClientPaymentInfoDTO> list = rentityList.stream().map((i) -> {
			ClientPaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
