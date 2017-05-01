package com.il.sod.rest.api.model;

import com.il.sod.db.dao.impl.PromotionDAO;
import com.il.sod.db.model.entities.Promotion;
import com.il.sod.db.model.repositories.PromotionRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PromotionMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PromotionDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/promotions", produces = MediaType.APPLICATION_JSON)
@Api(value = "/promotions", tags = {"promotions"})
public class PromotionService extends AbstractServiceMutations {

	@Autowired
	PromotionRepository promotionRepository;

	@Autowired
	PromotionDAO promotionDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Promotion", response = PromotionDTO.class)
	public Response savePromotion(PromotionDTO dto) throws SODAPIException {
		Promotion entity = PromotionMapper.INSTANCE.map(dto);
		this.saveEntity(promotionRepository, entity);
		dto = PromotionMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Promotion", response = PromotionDTO.class)
	public Response updatePromotion(PromotionDTO dto) throws SODAPIException {
		return updateEntity(dto);
	}

	private Response updateEntity(PromotionDTO dto) throws SODAPIException {

		Promotion entity = PromotionMapper.INSTANCE.map(dto);
		this.updateEntity(promotionRepository, entity);
		dto = PromotionMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Create Promotion", response = PromotionDTO.class)
	public Response deletePromotion(@PathVariable("id") String id, PromotionDTO dto) throws SODAPIException {

		Promotion entity = promotionRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Client not found");
		}
		this.softDeleteEntity(promotionRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Promotion list", response = PromotionDTO.class, responseContainer = "List")
	public Response getPromotionList(@QueryParam("name") String name) throws SODAPIException {
		List<Promotion> rentityList;
		if (!StringUtils.isEmpty(name)) {
			rentityList = promotionDAO.findByName(name);
		} else {
			rentityList = this.getEntityList(promotionRepository);
		}
		List<PromotionDTO> list = rentityList.stream()
				.map(PromotionMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}
}
