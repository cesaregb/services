package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.Asset;
import com.il.sod.db.model.repositories.AssetRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.AssetMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.AssetDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/asset", produces = "application/json")
//@Api(value = "/asset", tags = { "asset" })
public class AssetService extends AbstractServiceMutations {
	@Autowired
	AssetRepository assetRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Asset", response = AssetDTO.class)
	public ResponseEntity<AssetDTO> saveAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.saveEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Asset", response = AssetDTO.class)
	public ResponseEntity<AssetDTO> updateAsset(AssetDTO dto) throws SODAPIException {
		Asset entity = AssetMapper.INSTANCE.map(dto);
		this.updateEntity(assetRepository, entity);
		dto = AssetMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}


	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Task", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		Asset entity = assetRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(assetRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Asset list", response = AssetDTO.class, responseContainer = "List")
	public ResponseEntity<List<AssetDTO>> getAssetList() throws SODAPIException {
		List<Asset> entityList = this.getEntityList(assetRepository);
		List<AssetDTO> list = entityList.stream().map(AssetMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
