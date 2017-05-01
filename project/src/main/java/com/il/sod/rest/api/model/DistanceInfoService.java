package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.DistanceInfo;
import com.il.sod.db.model.repositories.DistanceInfoRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.StoreInfoMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.DistanceInfoDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/distance-info", produces = MediaType.APPLICATION_JSON)
@Api(value = "/distance-info", tags = {"app-utils"})
public class DistanceInfoService extends AbstractServiceMutations {

	@Autowired
	DistanceInfoRepository distanceInfoRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Distance Info", response = DistanceInfoDTO.class)
	public ResponseEntity<DistanceInfoDTO> saveDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		DistanceInfo entity = StoreInfoMapper.INSTANCE.map(dto);
		this.saveEntity(distanceInfoRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Distance Info", response = DistanceInfoDTO.class)
	public ResponseEntity<DistanceInfoDTO> updateDistanceInfo(DistanceInfoDTO dto) throws SODAPIException {
		DistanceInfo entity = StoreInfoMapper.INSTANCE.map(dto);
		this.updateEntity(distanceInfoRepository, entity);
		dto = StoreInfoMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		DistanceInfo entity = distanceInfoRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(distanceInfoRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Distance Info list", response = DistanceInfoDTO.class, responseContainer = "List")
	public ResponseEntity<List<DistanceInfoDTO>> getDistanceInfoList() throws SODAPIException {
		List<DistanceInfo> rentityList = distanceInfoRepository.findAllOrderByDistance();
		List<DistanceInfoDTO> list = rentityList.stream().map(StoreInfoMapper.INSTANCE::map).collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
