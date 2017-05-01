package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.ClientBag;
import com.il.sod.db.model.repositories.ClientBagRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientBagDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/clients/client-bag", produces = MediaType.APPLICATION_JSON)
@Api(value = "/clients/client-bag", tags = {"clients"})
public class ClientBagService extends AbstractServiceMutations {

	@Autowired
	ClientBagRepository clientBagRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Client Bag", response = ClientBagDTO.class)
	public ResponseEntity<ClientBagDTO> saveClientBag(ClientBagDTO dto) throws SODAPIException {
		ClientBag entity = ClientMapper.INSTANCE.map(dto);
		this.saveEntity(clientBagRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Client Bag", response = ClientBagDTO.class)
	public ResponseEntity<ClientBagDTO> updateClientBag(ClientBagDTO dto) throws SODAPIException {
		ClientBag entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(clientBagRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		ClientBag entity = clientBagRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(clientBagRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Client Bag list", response = ClientBagDTO.class, responseContainer = "List")
	public ResponseEntity<List<ClientBagDTO>> getClientBagList() throws SODAPIException {
		List<ClientBag> rentityList = clientBagRepository.findAll();
		List<ClientBagDTO> list = rentityList.stream().map(ClientMapper.INSTANCE::map).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
