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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/payment-info", produces = MediaType.APPLICATION_JSON)
@Api(value = "/payment-info", tags = {"payment"})
public class PaymentInfoService extends AbstractServiceMutations {

	@Autowired
	PaymentInfoRepository paymentInfoRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Payment Info", response = PaymentInfoDTO.class)
	public Response savePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {

		PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
		this.saveEntity(paymentInfoRepository, entity);
		dto = PaymentMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Payment Info", response = PaymentInfoDTO.class)
	public Response updatePaymentInfo(PaymentInfoDTO dto) throws SODAPIException {
		PaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
		this.updateEntity(paymentInfoRepository, entity);
		dto = PaymentMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete PaymentInfo", response = GeneralResponseMessage.class)
	public Response deleteEntity(@PathVariable("id") String id) throws SODAPIException {
		PaymentInfo entity = paymentInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "PaymentInfo not found");
		}

		this.deleteEntity(paymentInfoRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "PaymentInfo deleted"),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class, responseContainer = "List")
	public Response getPaymentInfoList() throws SODAPIException {
		List<PaymentInfo> rentityList = this.getEntityList(paymentInfoRepository);
		List<PaymentInfoDTO> list = rentityList.stream().map((i) -> {
			PaymentInfoDTO dto = PaymentMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{idPaymentInfo}")
	@ApiOperation(value = "Get Payment Info list", response = PaymentInfoDTO.class)
	public Response getPaymentInfoById(@PathVariable("idPaymentInfo") String idPaymentInfo) throws SODAPIException {
		if (!NumberUtils.isDigits(idPaymentInfo)) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Not a valid id " + idPaymentInfo);
		}
		PaymentInfo pi = this.getEntity(paymentInfoRepository, Integer.valueOf(idPaymentInfo));
		PaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
		return ConvertUtils.castEntityAsResponse(piDto);
	}

}
