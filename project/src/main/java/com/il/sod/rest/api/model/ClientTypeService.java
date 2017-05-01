package com.il.sod.rest.api.model;

import com.il.sod.db.dao.impl.ClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientType;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.ClientTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.ClientTypeDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/clients/client-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/client-type", tags = {"clients"})
public class ClientTypeService extends AbstractServiceMutations {

	@Autowired
	ClientTypeRepository clientTypeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ClientDAO clientDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Client Type", response = ClientTypeDTO.class)
	public ResponseEntity<ClientTypeDTO> saveClientType(ClientTypeDTO dto) throws SODAPIException {
		ClientType entity = ClientMapper.INSTANCE.map(dto);
		this.saveEntity(clientTypeRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Client Type", response = ClientTypeDTO.class)
	public ResponseEntity<ClientTypeDTO> updateClientType(ClientTypeDTO dto) throws SODAPIException {
		ClientType entity = ClientMapper.INSTANCE.map(dto);
		this.updateEntity(clientTypeRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Client Type", response = ClientTypeDTO.class)
	public ResponseEntity<GeneralResponseMessage> deleteClientType(@PathVariable("id") String id, ClientTypeDTO dto) throws SODAPIException {
		ClientType entity = clientTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Entity not found");
		}
		if (entity.getClients().size() > 0) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Entity Type have childs assigned.");
		}
		this.softDeleteEntity(clientTypeRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Client Type list", response = ClientTypeDTO.class, responseContainer = "List")
	public ResponseEntity<List<ClientTypeDTO>> getClientTypeList() throws SODAPIException {
		List<ClientType> rentityList = this.getEntityList(clientTypeRepository);
		List<ClientTypeDTO> list = rentityList.stream().map((i) -> {
			ClientTypeDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addClients/{idClientType}")
	@ApiOperation(value = "Add Client to Client Type", response = ClientTypeDTO.class, responseContainer = "List")
	public ResponseEntity<List<ClientTypeDTO>> addClient2ClientType(@PathVariable("idClientType") Integer idClientType, List<ClientDTO> dtoList) throws SODAPIException {
		List<ClientTypeDTO> result = new ArrayList<>();
		dtoList.forEach(dto -> {
			Client clientEntity = clientDAO.findByEmail(dto.getEmail());
			// remove me from the prev type.
			clientEntity.getClientType().removeClient(clientEntity);
			ClientType clientTypeEntity = clientTypeRepository.findOne(idClientType);
			clientTypeEntity.addClient(clientEntity);
			this.saveEntity(clientRepository, clientEntity);
			result.add(ClientMapper.INSTANCE.map(clientTypeEntity));
		});
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
