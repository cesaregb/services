package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.Store;
import com.il.sod.db.model.repositories.StoreRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.StoreInfoMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.StoreDTO;
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
@RequestMapping(value = "/stores", produces = MediaType.APPLICATION_JSON)
@Api(value = "/stores", tags = {"app-utils"})
public class StoreService extends AbstractServiceMutations {

	@Autowired
	StoreRepository storeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Store", response = StoreDTO.class)
	public Response saveStore(StoreDTO dto) throws SODAPIException {
		Store entity = StoreInfoMapper.INSTANCE.map(dto);
		this.saveEntity(storeRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Store", response = StoreDTO.class)
	public Response updateStore(StoreDTO dto) throws SODAPIException {
		Store entity = StoreInfoMapper.INSTANCE.map(dto);
		this.updateEntity(storeRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public Response deleteItem(@PathVariable("id") String id) throws SODAPIException {
		Store entity = storeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(storeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Store list", response = StoreDTO.class, responseContainer = "List")
	public Response getStoreList() throws SODAPIException {
		List<Store> rentityList = storeRepository.findAll();
		List<StoreDTO> list = rentityList.stream().map((i) -> {
			StoreDTO dto = StoreInfoMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{id}")
	@ApiOperation(value = "Get Store by ID", response = StoreDTO.class)
	public Response getStoreById(@PathVariable("id") Integer id) throws SODAPIException {
		Store entity = storeRepository.findOne(id);
		StoreDTO result = StoreInfoMapper.INSTANCE.map(entity);
		result.getDistanceInfos();
		return ConvertUtils.castEntityAsResponse(result);
	}

}
