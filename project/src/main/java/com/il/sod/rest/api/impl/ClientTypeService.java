package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.ClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientType;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.ClientTypeRepository;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.db.ClientTypeDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/clients/client-type")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/client-type", tags = { "clients" })
public class ClientTypeService extends AbstractServiceMutations {

	@Autowired
	ClientTypeRepository clientTypeRepository;

	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ClientDAO clientDAO;

	@POST
	@ApiOperation(value = "Create Client Type", response = ClientTypeDTO.class)
	public Response saveClientType(ClientTypeDTO dto) throws SODAPIException {

			ClientType entity = ClientMapper.INSTANCE.map(dto);
			this.saveEntity(clientTypeRepository, entity);
			dto = ClientMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Client Type", response = ClientTypeDTO.class)
	public Response updateClientType(ClientTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ClientTypeDTO dto) throws SODAPIException {

			ClientType entity = ClientMapper.INSTANCE.map(dto);
			this.updateEntity(clientTypeRepository, entity);
			dto = ClientMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Client Type", response = ClientTypeDTO.class)
	public Response deleteClientType(@PathParam("id") String id, ClientTypeDTO dto) throws SODAPIException {

			ClientType entity = clientTypeRepository.findOne(Integer.valueOf(id));

			if (entity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity not found");
			}

			if (entity.getClients().size() > 0){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity Type have childs assigned.");
			}

			this.softDeleteEntity(clientTypeRepository, entity.getId());
			return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);

	}

	@GET
	@ApiOperation(value = "Get Client Type list", response = ClientTypeDTO.class, responseContainer = "List")
	public Response getClientTypeList() throws SODAPIException {
		List<ClientType> rentityList = this.getEntityList(clientTypeRepository);
		List<ClientTypeDTO> list = rentityList.stream().map((i) -> {
			ClientTypeDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@POST
	@Path("/addClients/{idClientType}")
	@ApiOperation(value = "Add Client to Client Type", response = ClientTypeDTO.class, responseContainer = "List")
	public Response addClient2ClientType(@PathParam("idClientType") Integer idClientType, List<ClientDTO> dtoList) throws SODAPIException {

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
			return ConvertUtils.castEntityAsResponse(result, Response.Status.OK);

	}

}
