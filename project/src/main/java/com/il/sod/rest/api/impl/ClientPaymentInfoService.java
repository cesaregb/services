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

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.dao.IClientPaymentInfoDAO;
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

	@Autowired
	IClientPaymentInfoDAO clientPaymentInfoDAO;
	
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

	@Deprecated
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

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Payment Info", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateClientPaymentInfoById(@PathParam("id") String id, ClientPaymentInfoDTO dto) throws SODAPIException {
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
		List<ClientPaymentInfo> entityList = this.getEntityList(clientPaymentInfoRepository);
		List<ClientPaymentInfoDTO> list = entityList.stream().map((i) -> {
			ClientPaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
	@GET
	@Path("/{idClientPaymentInfo}")
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPaymentInfoById(@PathParam("idClientPaymentInfo") String idClientPaymentInfo) throws SODAPIException {
		if (!NumberUtils.isDigits(idClientPaymentInfo)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idClientPaymentInfo);
		}
		ClientPaymentInfo pi = this.getEntity(clientPaymentInfoRepository, Integer.valueOf(idClientPaymentInfo));
		ClientPaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
		return castEntityAsResponse(piDto);
	}
	
	@GET
	@Path("/client/{idClient}")
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPaymentInfoByClientId(@PathParam("idClient") String idClient) throws SODAPIException {
		if (!NumberUtils.isDigits(idClient)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idClient);
		}
		List<ClientPaymentInfo> entityList = clientPaymentInfoDAO.findByIdClient(Integer.valueOf(idClient));
		List<ClientPaymentInfoDTO> list = entityList.stream().map((i) -> {
			ClientPaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
