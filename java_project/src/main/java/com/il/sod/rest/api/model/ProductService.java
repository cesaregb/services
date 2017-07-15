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
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RolesAllowed("ADMIN")
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/products", tags = {"products"})
public class ProductService extends AbstractServiceMutations {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ProductDAO productDAO;



  @POST
  @ApiOperation(value = "Create Product", response = ProductDTO.class)
  public Response saveProduct(ProductDTO dto) throws SODAPIException {
    Product entity = ProductMapper.INSTANCE.map(dto);
    this.saveEntity(productRepository, entity);
    dto = ProductMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);
  }

  @PUT
  @ApiOperation(value = "Update Product", response = ProductDTO.class)
  public Response updateProduct(ProductDTO dto) throws SODAPIException {
    Product entity = productRepository.findOne(dto.getIdProduct());
    entity = ProductMapper.INSTANCE.map(dto, entity);
    this.updateEntity(productRepository, entity);
    dto = ProductMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
  }

  @DELETE
  @Path("/{id}")
  @ApiOperation(value = "Create Product", response = ProductDTO.class)
  public Response deleteProduct(@PathParam("id") String id, ProductDTO dto) throws SODAPIException {
    Product entity = productRepository.findOne(Integer.valueOf(id));
    if (entity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Client not found");
    }
    this.softDeleteEntity(productRepository, entity.getId());
    return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"), Response.Status.OK);
  }

  @GET
  @ApiOperation(value = "Get Product list", response = ProductDTO.class, responseContainer = "List")
  public Response getProductList(@QueryParam("name") String name,
                                 @QueryParam("idProductType") String idProductType) throws SODAPIException {
    List<Product> entityList;
    if (!StringUtils.isEmpty(name)) {
      entityList = productDAO.findByName(name);
    } else if (!StringUtils.isEmpty(idProductType)) {
      if (!NumberUtils.isNumber(idProductType)) {
        throw new SODAPIException(Response.Status.BAD_REQUEST, "{idProductType} not valid " + idProductType);
      }
      entityList = productDAO.findByProductType(Integer.valueOf(idProductType));
    } else {
      entityList = this.getEntityList(productRepository);
    }
    List<ProductDTO> list = entityList.stream()
            .map(ProductMapper.INSTANCE::map)
            .filter(DeletablePredicate.isActive())
            .collect(Collectors.toList());
    return ConvertUtils.castEntityAsResponse(list);
  }

  @POST
  @Path("/byProductTypes")
  @ApiOperation(value = "Get Product list by name", response = ProductDTO.class, responseContainer = "List")
  public Response getProductListByType(List<String> ids) throws SODAPIException {
    Set<ProductDTO> spSet = new HashSet<>();
    for (String item : ids) {
      List<Product> entityList = productDAO.findByProductType(Integer.valueOf(item));
      List<ProductDTO> list = entityList.stream()
              .map(ProductMapper.INSTANCE::map)
              .filter(DeletablePredicate.isActive())
              .collect(Collectors.toList());
      spSet.addAll(list);
    }
    List<ProductDTO> list = new ArrayList<>(spSet);
    return ConvertUtils.castEntityAsResponse(list);
  }

}
