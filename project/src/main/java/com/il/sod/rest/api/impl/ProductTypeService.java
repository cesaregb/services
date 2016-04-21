package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT; import javax.ws.rs.PathParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.ProductType;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ProductTypeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/product-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/product-type", tags = { "products" })
public class ProductTypeService extends AbstractServiceMutations {

	@Autowired
	ProductTypeRepository productTypeRepository;

	@POST
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveProductType(ProductTypeDTO dto) throws SODAPIException {
		try {
			ProductType entity = ProductMapper.INSTANCE.map(dto);
			this.saveEntity(productTypeRepository, entity);
			dto = ProductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Product Type", response = ProductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateProductType(ProductTypeDTO dto) throws SODAPIException {
		try {
			ProductType entity = ProductMapper.INSTANCE.map(dto);
			this.updateEntity(productTypeRepository, entity);
			dto = ProductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Product Type", response = ProductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateProductTypeById(@PathParam("id") String id, ProductTypeDTO dto) throws SODAPIException {
		try {
			ProductType entity = ProductMapper.INSTANCE.map(dto);
			this.updateEntity(productTypeRepository, entity);
			dto = ProductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Product Type", response = ProductTypeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteProductType(ProductTypeDTO dto) throws SODAPIException {
		try {
			ProductType entity = ProductMapper.INSTANCE.map(dto);
			this.deleteEntity(productTypeRepository, entity.getIdProductType());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Product deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Product Type list", response = ProductTypeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getProductTypeList() throws SODAPIException {
		List<ProductType> rentityList = this.getEntityList(productTypeRepository);
		List<ProductTypeDTO> list = rentityList.stream().map((i) -> {
			ProductTypeDTO dto = ProductMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
