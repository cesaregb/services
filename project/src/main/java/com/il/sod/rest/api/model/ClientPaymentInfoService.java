package com.il.sod.rest.api.model;

import com.il.sod.db.dao.IClientPaymentInfoDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientPaymentInfo;
import com.il.sod.db.model.repositories.ClientPaymentInfoRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.mapper.PaymentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientPaymentInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/clients/client-payment-info", produces = MediaType.APPLICATION_JSON)
@Api(value = "/clients/client-payment-info", tags = {"payment"})
public class ClientPaymentInfoService extends AbstractServiceMutations {
	private final static Logger LOGGER = LoggerFactory.getLogger(ClientPaymentInfoService.class);

	@Autowired
	ClientPaymentInfoRepository clientPaymentInfoRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	IClientPaymentInfoDAO clientPaymentInfoDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Client Payment Info", response = ClientPaymentInfoDTO.class)
	public ResponseEntity<ClientPaymentInfoDTO> saveClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);
		Client client = clientRepository.findOne(dto.getIdClient());
		ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
		if (client.getPaymentInfo(entity) != null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Client has already a Payment Info with that information.");
		}
		if (client.getClientPaymentInfos().size() == 0){
			entity.setPrefered(true);
		}
		client.addClientPaymentInfo(entity);
		client = this.saveEntity(clientRepository, client);
		dto = ClientMapper.INSTANCE.map(client.getPaymentInfo(entity));
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Client Payment Info", response = ClientPaymentInfoDTO.class)
	public ResponseEntity<ClientPaymentInfoDTO> updateClientPaymentInfo(ClientPaymentInfoDTO dto) throws SODAPIException {
		ClientPaymentInfo entity = PaymentMapper.INSTANCE.map(dto);
		this.updateEntity(clientPaymentInfoRepository, entity);
		dto = PaymentMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Client PaymentInfo", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteEntity(@PathVariable("id") String id) throws SODAPIException {
		ClientPaymentInfo entity = clientPaymentInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "ClientPaymentInfo not found");
		}
		Client cEntity = entity.getClient();
		cEntity.removeClientPaymentInfo(entity);
		this.saveEntity(clientRepository, cEntity);
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class, responseContainer = "List")
	public ResponseEntity<List<ClientPaymentInfoDTO>> getClientPaymentInfoList(@QueryParam("idClient") String idClient) throws SODAPIException {
		List<ClientPaymentInfo> entityList = null;
		if (!StringUtils.isEmpty(idClient)) {
			if (!NumberUtils.isDigits(idClient)) {
				throw new SODAPIException(HttpStatus.BAD_REQUEST, "Not a valid id " + idClient);
			}
			entityList = clientPaymentInfoDAO.findByIdClient(Integer.valueOf(idClient));
		} else {
			entityList = this.getEntityList(clientPaymentInfoRepository);
		}

		List<ClientPaymentInfoDTO> list = entityList.stream().map(PaymentMapper.INSTANCE::map).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{idClientPaymentInfo}")
	@ApiOperation(value = "Get Payment Info list", response = ClientPaymentInfoDTO.class)
	public ResponseEntity<ClientPaymentInfoDTO> getPaymentInfoById(@PathVariable("idClientPaymentInfo") String idClientPaymentInfo) throws SODAPIException {
		if (!NumberUtils.isDigits(idClientPaymentInfo)) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Not a valid id " + idClientPaymentInfo);
		}
		ClientPaymentInfo pi = this.getEntity(clientPaymentInfoRepository, Integer.valueOf(idClientPaymentInfo));
		ClientPaymentInfoDTO piDto = PaymentMapper.INSTANCE.map(pi);
		return new ResponseEntity<>(piDto, HttpStatus.OK);
	}

}
