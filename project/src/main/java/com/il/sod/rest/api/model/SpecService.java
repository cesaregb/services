package com.il.sod.rest.api.model;

import com.il.sod.db.dao.impl.SpecsValueDAO;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SpecDTO;
import com.il.sod.rest.dto.db.SpecsValueDTO;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping(value = "/specs", produces = MediaType.APPLICATION_JSON)
@Api(value = "/specs", tags = { "specs" })
public class SpecService extends AbstractServiceMutations {

	@Autowired
	SpecRepository specRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Spec", response = SpecDTO.class)
	public Response saveSpec(SpecDTO dto) throws SODAPIException {
		Spec entity = SpecsMapper.INSTANCE.map(dto);
		this.saveEntity(specRepository, entity);
		dto = SpecsMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Spec", response = SpecDTO.class)
	public Response updateSpec(SpecDTO dto) throws SODAPIException {
		Spec entity = SpecsMapper.INSTANCE.map(dto);
		this.updateEntity(specRepository, entity);
		dto = SpecsMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") String id) throws SODAPIException {
		Spec entity = specRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(specRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Spec list", response = SpecDTO.class, responseContainer = "List")
	public Response getAllSpecList() throws SODAPIException {
		List<Spec> entityList = this.getEntityList(specRepository);
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@Autowired
	private SupplyTypeRepository supplyTypeRepository;

	@Autowired
	private SpecsValueDAO specsValueDAO;
	@RequestMapping(method = RequestMethod.GET, value = "/{idSpecs}/specs-values")
	@ApiOperation(value = "Get Specs Value list by idSpec", response = SpecsValueDTO.class, responseContainer = "List")
	public Response getSpecsValuesById(@PathVariable("idSpecs") String idSpecs) throws SODAPIException {
		SpecsMapper.INSTANCE.setSupplyTypeRepository(supplyTypeRepository);
		List<SpecsValue> entityList = specsValueDAO.findBySpec(Integer.valueOf(idSpecs));
		List<SpecsValueDTO> list = entityList.stream().map((i) -> {
			SpecsValueDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
