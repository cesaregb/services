package com.il.sod.rest.api.model;

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
@RequestMapping(value = "/promotions/promotion-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/promotions/promotion-type", tags = {"promotions"})
public class PromotionTypeService extends AbstractServiceMutations {

	@Autowired
	PromotionTypeRepository promotionTypeRepository;

	@Autowired
	PromotionRepository promotionRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Promotion Type", response = PromotionTypeDTO.class)
	public Response savePromotionType(PromotionTypeDTO dto) throws SODAPIException {

		PromotionType entity = PromotionMapper.INSTANCE.map(dto);
		this.saveEntity(promotionTypeRepository, entity);
		dto = PromotionMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Promotion Type", response = PromotionTypeDTO.class)
	public Response updatePromotionType(PromotionTypeDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(PromotionTypeDTO dto) throws SODAPIException {

		PromotionType entity = PromotionMapper.INSTANCE.map(dto);
		this.updateEntity(promotionTypeRepository, entity);
		dto = PromotionMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Create Promotion Type", response = PromotionTypeDTO.class)
	public Response deletePromotionType(@PathVariable("id") String id, PromotionTypeDTO dto) throws SODAPIException {

		PromotionType entity = promotionTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Entity not found");
		}
		if (entity.getPromotions().size() > 0) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Entity Type have children assigned.");
		}

		this.softDeleteEntity(promotionTypeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Promotion Type list", response = PromotionTypeDTO.class, responseContainer = "List")
	public Response getPromotionTypeList() throws SODAPIException {
		List<PromotionType> rentityList = this.getEntityList(promotionTypeRepository);
		List<PromotionTypeDTO> list = rentityList.stream().map((i) -> {
			PromotionTypeDTO dto = PromotionMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/addPromotion/{idPromotionType}")
	@ApiOperation(value = "Add Promotion to Promotion Type", response = PromotionTypeDTO.class)
	public Response addPromotion2PromotionType(@PathVariable("idPromotionType") Integer id, PromotionDTO dto) throws SODAPIException {

		Promotion promotionEntity = promotionRepository.findOne(dto.getIdPromotion());
		if (promotionEntity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Promotion not found");
		}

		// remove me from the prev type.
		promotionEntity.getPromotionType().removePromotion(promotionEntity);
		PromotionType promotionTypeEntity = promotionTypeRepository.findOne(id);
		promotionTypeEntity.addPromotion(promotionEntity);
		this.saveEntity(promotionRepository, promotionEntity);
		PromotionTypeDTO result = PromotionMapper.INSTANCE.map(promotionTypeEntity);
		return ConvertUtils.castEntityAsResponse(result, HttpStatus.OK);

	}

}
