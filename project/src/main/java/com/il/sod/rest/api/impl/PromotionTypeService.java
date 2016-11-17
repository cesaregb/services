package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.Promotion;
import com.il.sod.db.model.entities.PromotionType;
import com.il.sod.db.model.repositories.PromotionRepository;
import com.il.sod.db.model.repositories.PromotionTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PromotionMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PromotionDTO;
import com.il.sod.rest.dto.db.PromotionTypeDTO;
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
@Path("/promotions/promotion-type")
@Produces(MediaType.APPLICATION_JSON)
 @Api(value = "/promotions/promotion-type", tags = { "promotions" })
public class PromotionTypeService extends AbstractServiceMutations {

	@Autowired
	PromotionTypeRepository promotionTypeRepository;

	@Autowired
	PromotionRepository promotionRepository;

	@POST
	@ApiOperation(value = "Create Promotion Type", response = PromotionTypeDTO.class)
	public Response savePromotionType(PromotionTypeDTO dto) throws SODAPIException {
		try {
			PromotionType entity = PromotionMapper.INSTANCE.map(dto);
			this.saveEntity(promotionTypeRepository, entity);
			dto = PromotionMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@ApiOperation(value = "Update Promotion Type", response = PromotionTypeDTO.class)
	public Response updatePromotionType(PromotionTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(PromotionTypeDTO dto) throws SODAPIException {
		try {
			PromotionType entity = PromotionMapper.INSTANCE.map(dto);
			this.updateEntity(promotionTypeRepository, entity);
			dto = PromotionMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Promotion Type", response = PromotionTypeDTO.class)
	public Response deletePromotionType(@PathParam("id") String id, PromotionTypeDTO dto) throws SODAPIException {
		try {
			PromotionType entity = promotionTypeRepository.findOne(Integer.valueOf(id));
			if (entity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity not found");
			}
			if (entity.getPromotions().size() > 0){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity Type have children assigned.");
			}

			this.softDeleteEntity(promotionTypeRepository, entity.getId());
			return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Promotion Type list", response = PromotionTypeDTO.class, responseContainer = "List")
	public Response getPromotionTypeList() throws SODAPIException {
		List<PromotionType> rentityList = this.getEntityList(promotionTypeRepository);
		List<PromotionTypeDTO> list = rentityList.stream().map((i) -> {
			PromotionTypeDTO dto = PromotionMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@POST
	@Path("/addPromotion/{idPromotionType}")
	@ApiOperation(value = "Add Promotion to Promotion Type", response = PromotionTypeDTO.class)
	public Response addPromotion2PromotionType(@PathParam("idPromotionType") Integer id, PromotionDTO dto) throws SODAPIException {
		try {
			Promotion promotionEntity = promotionRepository.findOne(dto.getIdPromotion());
			if (promotionEntity == null){
				throw new SODAPIException(Response.Status.BAD_REQUEST, "Promotion not found");
			}

			// remove me from the prev type.
			promotionEntity.getPromotionType().removePromotion(promotionEntity);
			PromotionType promotionTypeEntity = promotionTypeRepository.findOne(id);
			promotionTypeEntity.addPromotion(promotionEntity);
			this.saveEntity(promotionRepository, promotionEntity);
			PromotionTypeDTO result = PromotionMapper.INSTANCE.map(promotionTypeEntity);
			return castEntityAsResponse(result, Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

}
