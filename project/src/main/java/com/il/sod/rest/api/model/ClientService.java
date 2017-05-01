package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.services.cruds.ClientSv;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@RestController
@RolesAllowed("ADMIN")
@Api(value = "clients", tags = {"clients"})
@RequestMapping(value = "/clients", produces = MediaType.APPLICATION_JSON)
public class ClientService extends AbstractServiceMutations {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

	@Autowired
	ClientSv clientSv;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Client por mientras ", response = ClientDTO.class)
	public ResponseEntity<ClientDTO> saveClient(ClientDTO dto) throws SODAPIException {
		return new ResponseEntity<>(clientSv.saveClient(dto), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Client", response = ClientDTO.class)
	public ResponseEntity<ClientDTO> updateClient(ClientDTO dto) throws SODAPIException {
		return new ResponseEntity<>(clientSv.updateClient(dto), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/reactivate/{email}")
	@ApiOperation(value = "Reactivate Client", response = ClientDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class)})
	public ResponseEntity<ClientDTO> reactivateClient(@PathVariable("email") String email) throws SODAPIException {
		return new ResponseEntity<>(clientSv.reactivateClient(email), HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Client", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String clientId) throws SODAPIException {
		return new ResponseEntity<>(new GeneralResponseMessage(clientSv.deleteItem(Integer.valueOf(clientId)),
						"Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{clientId}")
	@ApiOperation(value = "Get Client by id", response = ClientDTO.class)
	public ResponseEntity<ClientDTO> getClient(@PathVariable("clientId") String clientId) throws SODAPIException {
		return new ResponseEntity<>(clientSv.getClient(clientId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Client list by filter UNIQUE[ phone, idAddress, email, token]", response = ClientDTO.class)
	public ResponseEntity<List<ClientDTO>> getClientsByFilter(@Context UriInfo uriInfo,
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

		return new ResponseEntity<>(clientSv.getClientsByFilter(uriInfo.getQueryParameters(), idAddress, phone, token),
				HttpStatus.OK);
	}

}