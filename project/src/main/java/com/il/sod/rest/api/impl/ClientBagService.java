package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ClientBag;
import com.il.sod.db.model.repositories.ClientBagRepository;
import com.il.sod.services.utils.ConvertUtils;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientBagDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Path("/clients/client-bag")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/clients/client-bag", tags = { "clients" })
public class ClientBagService extends AbstractServiceMutations {

	@Autowired
	ClientBagRepository clientBagRepository;

	@POST
	@ApiOperation(value = "Create Client Bag", response = ClientBagDTO.class)
	public Response saveClientBag(ClientBagDTO dto) throws SODAPIException {

			ClientBag entity = ClientMapper.INSTANCE.map(dto);
			this.saveEntity(clientBagRepository, entity);
			dto = ClientMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Client Bag", response = ClientBagDTO.class)
	public Response updateClientBag(ClientBagDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(ClientBagDTO dto) throws SODAPIException {

			ClientBag entity = ClientMapper.INSTANCE.map(dto);
			this.updateEntity(clientBagRepository, entity);
			dto = ClientMapper.INSTANCE.map(entity);
			return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);

	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		ClientBag entity = clientBagRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(clientBagRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Client Bag list", response = ClientBagDTO.class, responseContainer = "List")
	public Response getClientBagList() throws SODAPIException {
		List<ClientBag> rentityList = clientBagRepository.findAll();
		List<ClientBagDTO> list = rentityList.stream().map((i) -> {
			ClientBagDTO dto = ClientMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
