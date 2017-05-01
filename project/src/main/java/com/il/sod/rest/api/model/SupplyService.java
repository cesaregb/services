package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.Supply;
import com.il.sod.db.model.repositories.SupplyRepository;
import com.il.sod.db.model.repositories.SupplyTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.SupplyMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.SupplyDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
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
@RequestMapping(value = "/supplies", produces = MediaType.APPLICATION_JSON)
@Api(value = "/supplies", tags = {"supplies"})
public class SupplyService extends AbstractServiceMutations {
	@Autowired
	SupplyRepository supplyRepository;

	@Autowired
	SupplyTypeRepository supplyTypeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Supply", response = SupplyDTO.class)
	public Response saveSupply(SupplyDTO dto) throws SODAPIException {

		Supply entity = SupplyMapper.INSTANCE.map(dto);
		this.saveEntity(supplyRepository, entity);
		dto = SupplyMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Supply", response = SupplyDTO.class)
	public Response updateSupply(SupplyDTO dto) throws SODAPIException {
		Supply entity = SupplyMapper.INSTANCE.map(dto);
		this.updateEntity(supplyRepository, entity);
		dto = SupplyMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") String id) throws SODAPIException {
		Supply entity = supplyRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(supplyRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Supply list", response = SupplyDTO.class, responseContainer = "List")
	public Response getSupplyList() throws SODAPIException {
		List<Supply> entityList = this.getEntityList(supplyRepository);
		List<SupplyDTO> list = entityList.stream().map((i) -> {
			SupplyDTO dto = SupplyMapper.INSTANCE.map(i);
			return dto;
		})
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
