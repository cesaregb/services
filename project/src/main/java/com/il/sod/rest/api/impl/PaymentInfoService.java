package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.db.model.repositories.PaymentInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PaymentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PaymentInfoDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/payment-info")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/payment-info", tags = { "payment" })
public class PaymentInfoService extends AbstractServiceMutations {

	@Autowired
	PaymentInfoRepository paymentInfoRepository;

	@POST
	@ApiOperation(value = "Create Payment Info", response = PaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response savePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {
		try {
			PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.saveEntity(paymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Payment Info", response = PaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updatePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {
		try {
			PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.updateEntity(paymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Payment Info", response = PaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updatePaymentInfoById(@PathParam("id") String id, PaymentInfoDTO dto) throws SODAPIException {
		try {
			PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.updateEntity(paymentInfoRepository, entity);
			dto = PaymentMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Payment Info", response = PaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deletePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {
		try {
			PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
			this.deleteEntity(paymentInfoRepository, entity.getIdPaymentInfo());
			return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Payment Info deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPaymentInfoList() throws SODAPIException {
		List<PaymentInfo> rentityList = this.getEntityList(paymentInfoRepository);
		List<PaymentInfoDTO> list = rentityList.stream().map((i) -> {
			PaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
