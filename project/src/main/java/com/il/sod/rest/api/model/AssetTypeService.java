package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.AssetType;
import com.il.sod.db.model.repositories.AssetTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetTypeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
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
@RequestMapping(value = "/asset-type", produces = MediaType.APPLICATION_JSON)
//@Api(value = "/asset-type", tags = { "asset" })
public class AssetTypeService extends AbstractServiceMutations {
	@Autowired
	AssetTypeRepository assetTypeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Asset Type", response = AssetTypeDTO.class)
	public ResponseEntity<AssetTypeDTO> saveAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Asset Type", response = AssetTypeDTO.class)
	public ResponseEntity<AssetTypeDTO> updateAssetType(AssetTypeDTO dto) throws SODAPIException {
		AssetType entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetTypeRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		AssetType entity = assetTypeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(assetTypeRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Asset Type list", response = AssetTypeDTO.class, responseContainer = "List")
	public ResponseEntity<List<AssetTypeDTO>> getAssetTypeList() throws SODAPIException {
		List<AssetType> rentityList = this.getEntityList(assetTypeRepository);
		List<AssetTypeDTO> list = rentityList.stream().map(AssetMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.CREATED);
	}

}
