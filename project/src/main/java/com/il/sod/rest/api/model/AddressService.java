package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.AddressRepository;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AddressDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RolesAllowed("ADMIN")
@Api(value = "/clients/address", tags = { "clients" })
@RequestMapping(value = "/clients/address", produces = "application/json")
public class AddressService extends AbstractServiceMutations {

	private final static Logger LOGGER = getLogger(AddressService.class);

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	protected ClientRepository clientRepository;

	public AddressService() {
	}

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Address", response = AddressDTO.class)
	public ResponseEntity<AddressDTO> saveAddress(AddressDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);
		Client client = clientRepository.findOne(dto.getIdClient());
		Address entity = ClientMapper.INSTANCE.map(dto);
		if (client.getAddress(entity) != null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Client has already an address with that information.");
		}
		if (client.getAddresses().size() == 0){
			entity.setPrefered(true);
		}
		client.addAddress(entity);
		client = this.saveEntity(clientRepository, client);
		return new ResponseEntity<>(ClientMapper.INSTANCE.map(client.getAddress(entity)), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Address", response = AddressDTO.class)
	public ResponseEntity<AddressDTO> updateAddress(AddressDTO dto) throws SODAPIException {
		serviceDbHelper.validateClient(clientRepository, dto);
		Address entity = this.getEntity(addressRepository, dto.getIdAddress());
		entity = ClientMapper.INSTANCE.map(dto, entity);
		this.updateEntity(addressRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Address", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteEntity(@PathVariable("id") String id) throws SODAPIException {
		Address entity = addressRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Address not found");
		}
		Client cEntity = entity.getClient();
		cEntity.removeAddress(entity);
		this.saveEntity(clientRepository, cEntity);

		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Address list", response = AddressDTO.class, responseContainer = "List")
	public ResponseEntity<List<AddressDTO>> getAddressList(@Context UriInfo uriInfo,
								   @QueryParam("idClient") String idClient
								   // not implemented...
//								   @QueryParam("prefered") Boolean prefered,
//								   @QueryParam("factura") Boolean factura,
//								   @QueryParam("address") String address,
//								   @QueryParam("address2") String address2,
//								   @QueryParam("city") String city,
//								   @QueryParam("country") String country,
//								   @QueryParam("zipcode") String zipcode,
//								   @QueryParam("state") String state
									) throws SODAPIException {


		List<Address> rentityList = null;
		if (!StringUtils.isEmpty(idClient)){
			Client client = this.getEntity(clientRepository, Integer.valueOf(idClient));
			rentityList = new ArrayList<>(client.getAddresses());
		}else{
			rentityList = this.getEntityList(addressRepository);
		}

		List<AddressDTO> list = rentityList.stream().map(ClientMapper.INSTANCE::map).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{id}")
	@ApiOperation(value = "Get Stop by id", response = AddressDTO.class)
	public ResponseEntity<AddressDTO> getById(@PathVariable("id") String id) throws SODAPIException {
		Address entity = this.getEntity(addressRepository, Integer.valueOf(id));
		AddressDTO dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

}
