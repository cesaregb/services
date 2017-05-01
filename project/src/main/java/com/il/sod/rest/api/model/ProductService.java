package com.il.sod.rest.api.model;

import com.il.sod.db.dao.impl.ProductDAO;
import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ProductMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.ProductDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import com.il.sod.services.utils.ConvertUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/products", produces = MediaType.APPLICATION_JSON)
@Api(value = "/products", tags = {"products"})
public class ProductService extends AbstractServiceMutations {

	@Autowired
	ProductRepository productRepository;

	@Autowired
	ProductDAO productDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Product", response = ProductDTO.class)
	public Response saveProduct(ProductDTO dto) throws SODAPIException {
		Product entity = ProductMapper.INSTANCE.map(dto);
		this.saveEntity(productRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Product", response = ProductDTO.class)
	public Response updateProduct(ProductDTO dto) throws SODAPIException {
		Product entity = ProductMapper.INSTANCE.map(dto);
		this.updateEntity(productRepository, entity);
		dto = ProductMapper.INSTANCE.map(entity);
		return ConvertUtils.castEntityAsResponse(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Create Product", response = ProductDTO.class)
	public Response deleteProduct(@PathVariable("id") String id, ProductDTO dto) throws SODAPIException {
		Product entity = productRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Client not found");
		}
		this.softDeleteEntity(productRepository, entity.getId());
		return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Product list", response = ProductDTO.class, responseContainer = "List")
	public Response getProductList(@QueryParam("name") String name,
	                               @QueryParam("idProductType") String idProductType) throws SODAPIException {
		List<Product> rentityList;
		if (!StringUtils.isEmpty(name)) {
			rentityList = productDAO.findByName(name);
		} else if (!StringUtils.isEmpty(idProductType)) {
			if (!NumberUtils.isNumber(idProductType)) {
				throw new SODAPIException(HttpStatus.BAD_REQUEST, "{idProductType} not valid " + idProductType);
			}
			rentityList = productDAO.findByProductType(Integer.valueOf(idProductType));
		} else {
			rentityList = this.getEntityList(productRepository);
		}
		List<ProductDTO> list = rentityList.stream()
				.map(ProductMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return ConvertUtils.castEntityAsResponse(list);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/byProductTypes")
	@ApiOperation(value = "Get Product list by name", response = ProductDTO.class, responseContainer = "List")
	public Response getProductListByType(List<String> ids) throws SODAPIException {
		Set<ProductDTO> spSet = new HashSet<>();
		for (String item : ids) {
			List<Product> rentityList = productDAO.findByProductType(Integer.valueOf(item));
			List<ProductDTO> list = rentityList.stream()
					.map(ProductMapper.INSTANCE::map)
					.filter(DeletablePredicate.isActive())
					.collect(Collectors.toList());
			spSet.addAll(list);
		}
		List<ProductDTO> list = new ArrayList<>(spSet);
		return ConvertUtils.castEntityAsResponse(list);
	}

}
