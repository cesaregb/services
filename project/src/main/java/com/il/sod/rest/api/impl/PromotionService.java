package com.il.sod.rest.api.impl;

import com.il.sod.db.dao.impl.PromotionDAO;
import com.il.sod.db.model.entities.Promotion;
import com.il.sod.db.model.repositories.PromotionRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PromotionMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PromotionDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Path("/promotions")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/promotions", tags = { "promotions" })
public class PromotionService extends AbstractServiceMutations {

	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	PromotionDAO promotionDAO;

	@POST
	@ApiOperation(value = "Create Promotion", response = PromotionDTO.class)
	public Response savePromotion(PromotionDTO dto) throws SODAPIException {
		try {
			Promotion entity = PromotionMapper.INSTANCE.map(dto);
			this.saveEntity(promotionRepository, entity);
			dto = PromotionMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Promotion", response = PromotionDTO.class)
	public Response updatePromotion(PromotionDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(PromotionDTO dto) throws SODAPIException {
		try {
			Promotion entity = PromotionMapper.INSTANCE.map(dto);
			this.updateEntity(promotionRepository, entity);
			dto = PromotionMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Promotion", response = PromotionDTO.class)
	public Response deletePromotion(@PathParam("id") String id, PromotionDTO dto) throws SODAPIException {
		try {
			Promotion entity = promotionRepository.findOne(Integer.valueOf(id));
			if (entity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not found");
			}
			this.softDeleteEntity(promotionRepository, entity.getId());
			return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Entity deleted"),
					Response.Status.OK);

		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Promotion list", response = PromotionDTO.class, responseContainer = "List")
	public Response getPromotionList(@QueryParam("name") String name) throws SODAPIException {
		List<Promotion> rentityList = null;
		if (!StringUtils.isEmpty(name)){
			rentityList = promotionDAO.findByName(name);
		}else{
			rentityList = this.getEntityList(Promotion.class, promotionRepository);
		}
		List<PromotionDTO> list = rentityList.stream().map(PromotionMapper.INSTANCE::map).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
}
