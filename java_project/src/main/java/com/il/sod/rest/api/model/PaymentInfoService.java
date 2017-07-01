package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.PaymentInfo;
import com.il.sod.db.model.repositories.PaymentInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PaymentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PaymentInfoDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/payment-info", tags = {"payment"})
public class PaymentInfoService extends AbstractServiceMutations {

  @Autowired
  PaymentInfoRepository paymentInfoRepository;

  @POST
  @ApiOperation(value = "Create Payment Info", response = PaymentInfoDTO.class)
  public Response savePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {

    PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
    this.saveEntity(paymentInfoRepository, entity);
    dto = PaymentMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

  }

  @PUT
  @ApiOperation(value = "Update Payment Info", response = PaymentInfoDTO.class)
  public Response updatePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {
    return updateEntity(dto);
  }

  private Response updateEntity(PaymentInfoDTO dto) throws SODAPIException {

    PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
    this.updateEntity(paymentInfoRepository, entity);
    dto = PaymentMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);

  }

  @DELETE
  @Path("/{id}")
  @ApiOperation(value = "Delete PaymentInfo", response = GeneralResponseMessage.class)
  public Response deleteEntity(@PathParam("id") String id) throws SODAPIException {
    PaymentInfo entity = paymentInfoRepository.findOne(Integer.valueOf(id));
    if (entity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "PaymentInfo not found");
    }

    this.deleteEntity(paymentInfoRepository, entity.getId());
    return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "PaymentInfo deleted"),
            Response.Status.OK);

  }

  @GET
  @ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class, responseContainer = "List")
  public Response getPaymentInfoList() throws SODAPIException {
    List<PaymentInfo> entityList = this.getEntityList(paymentInfoRepository);
    List<PaymentInfoDTO> list = entityList.stream().map((i) -> {
      PaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
      return dto;
    }).collect(Collectors.toList());
    return ConvertUtils.castEntityAsResponse(list);
  }

  @GET
  @Path("/byId/{idPaymentInfo}")
  @ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class)
  public Response getPaymentInfoById(@PathParam("idPaymentInfo") String idPaymentInfo) throws SODAPIException {
    if (!NumberUtils.isDigits(idPaymentInfo)) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Not a valid id " + idPaymentInfo);
    }
    PaymentInfo pi = this.getEntity(paymentInfoRepository, Integer.valueOf(idPaymentInfo));
    PaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
    return ConvertUtils.castEntityAsResponse(piDto);
  }

}
