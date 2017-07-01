package com.il.sod.rest.api.model;

import com.il.sod.db.model.entities.PriceAdjustment;
import com.il.sod.db.model.entities.PriceAdjustmentType;
import com.il.sod.db.model.repositories.PriceAdjustmentRepository;
import com.il.sod.db.model.repositories.PriceAdjustmentTypeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.PriceAdjustmentMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.PriceAdjustmentDTO;
import com.il.sod.rest.dto.db.PriceAdjustmentTypeDTO;
import com.il.sod.services.utils.ConvertUtils;
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
@Path("/priceAdjustments/priceAdjustment-type")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/priceAdjustments/priceAdjustment-type", tags = {"priceAdjustments"})
public class PriceAdjustmentTypeService extends AbstractServiceMutations {

  @Autowired
  private PriceAdjustmentTypeRepository priceAdjustmentTypeRepository;

  @Autowired
  private PriceAdjustmentRepository priceAdjustmentRepository;

  @POST
  @ApiOperation(value = "Create PriceAdjustment Type", response = PriceAdjustmentTypeDTO.class)
  public Response savePriceAdjustmentType(PriceAdjustmentTypeDTO dto) throws SODAPIException {

    PriceAdjustmentType entity = PriceAdjustmentMapper.INSTANCE.map(dto);
    this.saveEntity(priceAdjustmentTypeRepository, entity);
    dto = PriceAdjustmentMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.CREATED);

  }

  @PUT
  @ApiOperation(value = "Update PriceAdjustment Type", response = PriceAdjustmentTypeDTO.class)
  public Response updatePriceAdjustmentType(PriceAdjustmentTypeDTO dto) throws SODAPIException {
    PriceAdjustmentType entity = PriceAdjustmentMapper.INSTANCE.map(dto);
    this.updateEntity(priceAdjustmentTypeRepository, entity);
    dto = PriceAdjustmentMapper.INSTANCE.map(entity);
    return ConvertUtils.castEntityAsResponse(dto, Response.Status.OK);
  }

  @DELETE
  @Path("/{id}")
  @ApiOperation(value = "Create PriceAdjustment Type", response = PriceAdjustmentTypeDTO.class)
  public Response deletePriceAdjustmentType(@PathParam("id") String id, PriceAdjustmentTypeDTO dto) throws SODAPIException {

    PriceAdjustmentType entity = priceAdjustmentTypeRepository.findOne(Integer.valueOf(id));
    if (entity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity not found");
    }
    if (!entity.getPriceAdjustments().isEmpty()) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Entity Type have children assigned.");
    }

    this.softDeleteEntity(priceAdjustmentTypeRepository, entity.getId());
    return ConvertUtils.castEntityAsResponse(new GeneralResponseMessage(true, "Entity deleted"),
            Response.Status.OK);

  }

  @GET
  @ApiOperation(value = "Get PriceAdjustment Type list", response = PriceAdjustmentTypeDTO.class, responseContainer = "List")
  public Response getPriceAdjustmentTypeList() throws SODAPIException {
    List<PriceAdjustmentType> entityList = this.getEntityList(priceAdjustmentTypeRepository);
    List<PriceAdjustmentTypeDTO> list = entityList.stream().map((i) -> {
      PriceAdjustmentTypeDTO dto = PriceAdjustmentMapper.INSTANCE.map(i);
      return dto;
    }).collect(Collectors.toList());
    return ConvertUtils.castEntityAsResponse(list);
  }

  @POST
  @Path("/addPriceAdjustment/{idPriceAdjustmentType}")
  @ApiOperation(value = "Add PriceAdjustment to PriceAdjustment Type", response = PriceAdjustmentTypeDTO.class)
  public Response addPriceAdjustment2PriceAdjustmentType(@PathParam("idPriceAdjustmentType") Integer id, PriceAdjustmentDTO dto) throws SODAPIException {

    PriceAdjustment priceAdjustmentEntity = priceAdjustmentRepository.findOne(dto.getIdPriceAdjustment());
    if (priceAdjustmentEntity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "PriceAdjustment not found");
    }

    // remove me from the prev type.
    priceAdjustmentEntity.getPriceAdjustmentType().removePriceAdjustment(priceAdjustmentEntity);
    PriceAdjustmentType priceAdjustmentTypeEntity = priceAdjustmentTypeRepository.findOne(id);
    priceAdjustmentTypeEntity.addPriceAdjustment(priceAdjustmentEntity);
    this.saveEntity(priceAdjustmentRepository, priceAdjustmentEntity);
    PriceAdjustmentTypeDTO result = PriceAdjustmentMapper.INSTANCE.map(priceAdjustmentTypeEntity);
    return ConvertUtils.castEntityAsResponse(result, Response.Status.OK);

  }

}
