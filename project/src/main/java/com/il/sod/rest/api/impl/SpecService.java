package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.SpecsValueDAO;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.SpecRepository;
import com.il.sod.db.model.repositories.SpecsValueRepository;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SpecDTO;
import com.il.sod.rest.dto.db.SpecsValueDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Path("/specs")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/specs", tags = { "specs" })
public class SpecService extends AbstractServiceMutations {

	@Autowired
	SpecRepository specRepository;

	@POST
	@ApiOperation(value = "Create Spec", response = SpecDTO.class)
	public Response saveSpec(SpecDTO dto) throws SODAPIException {
		try {
			Spec entity = SpecsMapper.INSTANCE.map(dto);
			this.saveEntity(specRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Spec", response = SpecDTO.class)
	public Response updateSpec(SpecDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(SpecDTO dto) throws SODAPIException {
		try {
			Spec entity = SpecsMapper.INSTANCE.map(dto);
			this.updateEntity(specRepository, entity);
			dto = SpecsMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathParam("id") String id) throws SODAPIException {
		Spec entity = specRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(specRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Spec list", response = SpecDTO.class, responseContainer = "List")
	public Response getAllSpecList() throws SODAPIException {
		List<Spec> entityList = this.getEntityList(specRepository);
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byNotPrimary")
	@ApiOperation(value = "Get Spec list Not Primary", response = SpecDTO.class, responseContainer = "List")
	public Response getSpecList() throws SODAPIException {
		List<Spec> entityList = specRepository.findAllNotPrimary();

		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/byPrimary")
	@ApiOperation(value = "Get Spec list Primary", response = SpecDTO.class, responseContainer = "List")
	public Response getSpecListByPrimary() throws SODAPIException {
		List<Spec> entityList = specRepository.findAllPrimary();
		List<SpecDTO> list = entityList.stream().map((i) -> {
			SpecDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}


	@Autowired
	SupplyTypeRepository supplyTypeRepository;
	@Autowired
	private SpecsValueDAO specsValueDAO;
	@GET
	@Path("/{idSpecs}/specs-values")
	@ApiOperation(value = "Get Specs Value list by idSpec", response = SpecsValueDTO.class, responseContainer = "List")
	public Response getSpecsValuesById(@PathParam("idSpecs") String idSpecs) throws SODAPIException {
		SpecsMapper.INSTANCE.setSupplyTypeRepository(supplyTypeRepository);
		List<SpecsValue> entityList = specsValueDAO.findBySpec(Integer.valueOf(idSpecs));
		List<SpecsValueDTO> list = entityList.stream().map((i) -> {
			SpecsValueDTO dto = SpecsMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
