package com.il.sod.rest.api.impl;

import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
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
@Path("/products/product-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/products/product-type", tags = {"products"})
public class ProductTypeService extends AbstractServiceMutations {

	@Autowired
	ProductTypeRepository productTypeRepository;

	@POST
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	public Response saveProductType(ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = ProductMapper.INSTANCE.map(dto);
		this.saveEntity(productTypeRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.CREATED);

	}

	@PUT
	@ApiOperation(value = "Update Product Type", response = ProductTypeDTO.class)
	public Response updateProductType(ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = ProductMapper.INSTANCE.map(dto);
		this.updateEntity(productTypeRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return castEntityAsResponse(dto, Response.Status.OK);
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	public Response deleteProductType(@PathParam("id") String id, ProductTypeDTO dto) throws SODAPIException {
		ProductType entity = productTypeRepository.findOne(Integer.valueOf(id));

		if (entity == null) {
			throw new SODAPIException(Response.Status.NO_CONTENT, "Entity not found");
		}

		if (entity.getProducts().size() > 0) {
			throw new SODAPIException(Response.Status.NO_CONTENT, "Entity Type have childs assigned.");
		}

		this.softDeleteEntity(productTypeRepository, entity.getId());
		return castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Product Type list", response = ProductTypeDTO.class, responseContainer = "List")
	public Response getProductTypeList() throws SODAPIException {
		List<ProductType> rentityList = this.getEntityList(productTypeRepository);
		List<ProductTypeDTO> list = rentityList.stream()
				.map(ProductMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
