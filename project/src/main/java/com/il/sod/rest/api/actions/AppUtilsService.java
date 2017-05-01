package com.il.sod.rest.api.actions;

import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.model.entities.Menu;
import com.il.sod.db.model.repositories.MenuRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.db.MenuDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/app-utils", produces = MediaType.APPLICATION_JSON)
@Api(value = "/app-utils", tags = { "app-utils" })
public class AppUtilsService extends AbstractServiceMutations {
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	SpecificObjectsConverterService specificObjectsConverterService;

	@RequestMapping(method = RequestMethod.GET, value = "/menu")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response getMenu() throws SODAPIException {
		List<Menu> entities = menuRepository.findAllByOrderByOrderAsc();
		List<MenuDTO> dtos = entities.stream().map(i -> {return specificObjectsConverterService.map(i);}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(dtos);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/menu/{accessLevel}")
	@ApiOperation(value = "Get Menu Options", response = MenuDTO.class, responseContainer = "List")
	public Response getMenu(@PathVariable("accessLevel") String accessLevel) throws SODAPIException {
		List<Menu> entities = menuRepository.findAll();
		// TODO create enum for handling roles.
		final int accessL = (accessLevel.equals("admin"))?1:2;
		List<MenuDTO> dtos = entities.stream()
				.filter(i -> i.getAccessLevel() >= accessL)
				.map(i -> {return specificObjectsConverterService.map(i);})
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(dtos);
	}
}
