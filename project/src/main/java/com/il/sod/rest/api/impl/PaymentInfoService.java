package com.il.sod.rest.api.impl;

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
import org.apache.commons.lang3.math.NumberUtils;
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
		return updateEntity(dto);
	}

	private Response updateEntity(PaymentInfoDTO dto) throws SODAPIException {
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
		return updateEntity(dto);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete PaymentInfo", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
		PaymentInfo entity = paymentInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "PaymentInfo not found");
		}
		
		this.deleteEntity(paymentInfoRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("PaymentInfo deleted"),
				Response.Status.OK);
		
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
	
	@GET
	@Path("/byId/{idPaymentInfo}")
	@ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getPaymentInfoById(@PathParam("idPaymentInfo") String idPaymentInfo) throws SODAPIException {
		if (!NumberUtils.isDigits(idPaymentInfo)){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idPaymentInfo);
		}
		PaymentInfo pi = this.getEntity(paymentInfoRepository, Integer.valueOf(idPaymentInfo));
		PaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
		return castEntityAsResponse(piDto);
	}

}
