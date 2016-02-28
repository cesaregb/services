package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.db.model.repositories.ProductTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ProductDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/product", tags = { "product" })
public class ProductService extends AbstractServiceMutations {
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductTypeRepository productTypeRepository;
	
	@PUT
	@ApiOperation(value = "Create Product", response = ProductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveProduct(ProductDTO dto) throws SODAPIException {
		try {
			Product entity = ProductMapper.INSTANCE.map(dto);
			this.saveEntity(productRepository, entity);
			dto = ProductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Product", response = ProductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateProduct(ProductDTO dto) throws SODAPIException {
		try {
			Product entity = ProductMapper.INSTANCE.map(dto);
			this.updateEntity(productRepository, entity);
			dto = ProductMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

//	private ProductType getProductType(Product entity, int idProductType) {
//		entity.setProductType(getProductType(entity, dto.getProductTypeId()));
//		ProductType tt = this.getEntity(productTypeRepository, idProductType);
//		tt.addProduct(entity);
//		return tt;
//	}

	
	@DELETE
	@ApiOperation(value = "Create Product", response = ProductDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteProduct(ProductDTO dto) throws SODAPIException {
		try {
			Product entity = ProductMapper.INSTANCE.map(dto);
			this.deleteEntity(productRepository, entity.getIdProduct());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Product deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}
	
	@GET
	@ApiOperation(value = "Get Product list", response = ProductDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getProductList() throws SODAPIException {
		List<Product> entityList = this.getEntityList(productRepository);
		List<ProductDTO> list = entityList.stream().map((i) -> {
			ProductDTO dto = ProductMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}
	
}