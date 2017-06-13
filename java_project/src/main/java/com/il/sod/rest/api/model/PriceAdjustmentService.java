package com.il.sod.rest.api.model;

import com.il.sod.db.dao.impl.PriceAdjustmentDAO;
import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.repositories.PriceAdjustmentRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PriceAdjustmentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PriceAdjustmentDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
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
@Path("/priceAdjustments")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/priceAdjustments", tags = {"priceAdjustments"})
public class PriceAdjustmentService extends AbstractServiceMutations {

	@Autowired
	PriceAdjustmentRepository priceAdjustmentRepository;

	@Autowired
	PriceAdjustmentDAO priceAdjustmentDAO;

	@POST
	@ApiOperation(value = "Create PriceAdjustment", response = PriceAdjustmentDTO.class)
	public Response savePriceAdjustment(PriceAdjustmentDTO dto) throws SODAPIException {
		PriceAdjustment entity = PriceAdjustmentMapper.INSTANCE.map(dto);
		this.saveEntity(priceAdjustmentRepository, entity);
		dto = PriceAdjustmentMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);
	}

	@PUT
	@ApiOperation(value = "Update PriceAdjustment", response = PriceAdjustmentDTO.class)
	public Response updatePriceAdjustment(PriceAdjustmentDTO dto) throws SODAPIException {
		PriceAdjustment entity = PriceAdjustmentMapper.INSTANCE.map(dto);
		this.updateEntity(priceAdjustmentRepository, entity);
		dto = PriceAdjustmentMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create PriceAdjustment", response = PriceAdjustmentDTO.class)
	public Response deletePriceAdjustment(@PathParam("id") String id, PriceAdjustmentDTO dto) throws SODAPIException {

		PriceAdjustment entity = priceAdjustmentRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not found");
		}
		this.softDeleteEntity(priceAdjustmentRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get PriceAdjustment list", response = PriceAdjustmentDTO.class, responseContainer = "List")
	public Response getPriceAdjustmentList(@QueryParam("name") String name) throws SODAPIException {
		List<PriceAdjustment> entityList;
		if (!StringUtils.isEmpty(name)) {
			entityList = priceAdjustmentDAO.findByName(name);
		} else {
			entityList = this.getEntityList(priceAdjustmentRepository);
		}
		List<PriceAdjustmentDTO> list = entityList.stream()
				.map(PriceAdjustmentMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}
}
