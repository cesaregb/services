package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.ClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.helper.ListsHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "clients", tags = { "clients" })
@Path("/clients")
public class ClientService extends AbstractServiceMutations {

	private static final String PHONE_TXT = "phone";

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	protected ClientRepository clientRepository;
	
	@Autowired
	protected ClientTypeRepository clientTypeRepository;

	@Autowired
	protected ClientDAO clientDAO;

	@POST
	@ApiOperation(value = "Create Client", response = ClientDTO.class)
	public Response saveClient(ClientDTO dto) throws SODAPIException {

		if (dto.getIdClientType() == null || dto.getIdClientType() == 0){
			final String localMessage = "Client Type should not be empty";
			LOGGER.error(localMessage);
			throw new SODAPIException(Response.Status.BAD_REQUEST, localMessage);
		}

		if (clientDAO.findByEmail(dto.getEmail()) != null){
			final String localMesage = "Email already associated with another User ";
			LOGGER.error(localMesage);
			throw new SODAPIException(Response.Status.BAD_REQUEST, localMesage);
		}


		Client entity = ClientMapper.INSTANCE.map(dto);
		assignDependencyToChilds(entity);
		this.saveEntity(clientRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update Client", response = ClientDTO.class)
	public Response updateClient(ClientDTO dto) throws SODAPIException {
		// find existing one.
		Client entity = clientDAO.findByEmail(dto.getEmail());

		if (entity != null && entity.getDeleted() > 0){
            throw new SODAPIException(Response.Status.NOT_FOUND, "Client deleted, please request admin to activate.");
		}

		if (entity == null){
			LOGGER.info("Note update, call saveClient");
			return this.saveClient(dto);
		}else{ // Update entity
			dto.setIdClient(entity.getId());// assign because we are seeking from email not id.
			LOGGER.info("Update entity");
			// remove dependencies to be udated..
			entity.getClientType().removeClient(entity);
			entity = ClientMapper.INSTANCE.map(dto, entity);
			LOGGER.info("***** Client ID " + entity.getId());
			clientTypeRepository.findOne(dto.getIdClientType()).addClient(entity);
		}

		assignDependencyToChilds(entity);
		this.updateEntity(clientRepository, entity);
		dto = ClientMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

    @PUT
    @Path("/reactivate/{email}")
    @ApiOperation(value = "Reactivate Client", response = ClientDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
            @ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
    public Response reactivateCient(@PathParam("email") String email) throws SODAPIException {
        Client entity = clientDAO.findByEmail(email);
        if (entity == null){
            throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found with email: "+email+" ");
        }
        LOGGER.info("Update entity");
        entity.setDeleted(0);
        this.updateEntity(clientRepository, entity);
        ClientDTO dto = ClientMapper.INSTANCE.map(entity);
        return castEntityAsResponse(dto, Response.Status.OK);
    }


	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String clientId) throws SODAPIException {
		Client entity = clientRepository.findOne(Integer.valueOf(clientId));
		if (entity == null){
			throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found");
		}
		this.softDeleteEntity(clientRepository, entity.getIdClient());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@Path("/byId/{clientId}")
	@ApiOperation(value = "Get Client list", response = ClientDTO.class)
	public Response getClient(@PathParam("clientId") String clientId) throws SODAPIException {
		ClientDTO dto = ClientMapper.INSTANCE.map(this.getEntity(clientRepository, Integer.valueOf(clientId)));
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Client list by filter UNIQUE[ phone, idAddress, email, token]", response = ClientDTO.class)
	public Response getClientsByFilter(@Context UriInfo uriInfo,
									   @QueryParam("idAddress") String idAddress,
									   @QueryParam("phone") String phone,
									   @QueryParam("token") String token,
									   @QueryParam("email") String email,
									   @QueryParam("lastName") String lastName,
									   @QueryParam("name") String name,
									   @QueryParam("twitter") String twitter,
									   @QueryParam("loginID") String loginID,
									   @QueryParam("rfc") String rfc,
									   @QueryParam("razonSocial") String razonSocial,
									   @QueryParam("idClientType") String idClientType) throws SODAPIException {

		MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();


		// logging
        Iterator<String> it;
		if (!queryParams.isEmpty()){
            it = queryParams.keySet().iterator();
            while(it.hasNext()){
                String theKey = it.next();
                LOGGER.info(theKey + " : " + queryParams.getFirst(theKey));
            }
        }

		List<Client> entities = null;
		if (StringUtils.isNotEmpty(phone)) {
			entities = clientDAO.findByPhone(phone);
		}else if (StringUtils.isNotEmpty(idAddress)){
			entities = clientDAO.findByAddress(Integer.valueOf(idAddress));
		}else if (StringUtils.isNotEmpty(token)){
			entities = clientDAO.findByToken(token);
		}else if (!queryParams.isEmpty()){
			List<ClientSpecification> filterList = new ArrayList<>();
			it = queryParams.keySet().iterator();
			while(it.hasNext()){
				String theKey = it.next();
				if (!theKey.toLowerCase().equals(PHONE_TXT)){
					ClientSpecification spec = new ClientSpecification(new SearchCriteria(theKey, ":", queryParams.getFirst(theKey)));
					filterList.add(spec);
				}
			}
			SpecificationsBuilder<Client, ClientSpecification> builder = new SpecificationsBuilder<>(filterList);
			entities = clientRepository.findAll(builder.build());
		}else{
			entities = this.getEntityList(clientRepository);
		}

		List<ClientDTO> result = ListsHelper.getActiveEntityList(entities).stream().map(ClientMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(result, Response.Status.OK);
	}

	private void assignDependencyToChilds(Client entity) {
		if (entity.getAddresses() != null)
			entity.getAddresses().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getAccessKeys() != null)
			entity.getAccessKeys().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getOrders() != null)
			entity.getOrders().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
		if (entity.getClientPaymentInfos() != null)
			entity.getClientPaymentInfos().stream().filter(a -> a != null).forEach(a -> a.setClient(entity));
	}
}