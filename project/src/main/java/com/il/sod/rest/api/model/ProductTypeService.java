package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/products/product-type", produces = MediaType.APPLICATION_JSON)
@Api(value = "/products/product-type", tags = {"products"})
public class ProductTypeService extends AbstractServiceMutations {

	@Autowired
	ProductTypeRepository productTypeRepository;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	public Response saveProductType(ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = ProductMapper.INSTANCE.map(dto);
		this.saveEntity(productTypeRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Product Type", response = ProductTypeDTO.class)
	public Response updateProductType(ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = ProductMapper.INSTANCE.map(dto);
		this.updateEntity(productTypeRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE)
	@RequestMapping(value = "/{id}")
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	public Response deleteProductType(@PathVariable("id") String id, ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = productTypeRepository.findOne(Integer.valueOf(id));

		if (entity == null) {
			throw new SODAPIException(HttpStatus.NO_CONTENT, "Entity not found");
		}

		if (entity.getProducts().size() > 0) {
			throw new SODAPIException(HttpStatus.NO_CONTENT, "Entity Type have childs assigned.");
		}

		this.softDeleteEntity(productTypeRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Product Type list", response = ProductTypeDTO.class, responseContainer = "List")
	public Response getProductTypeList() throws SODAPIException {
		List<ProductType> rentityList = this.getEntityList(productTypeRepository);
		List<ProductTypeDTO> list = rentityList.stream()
				.map(ProductMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

}
