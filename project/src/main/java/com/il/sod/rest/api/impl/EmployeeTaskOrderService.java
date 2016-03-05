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

import com.il.sod.db.model.entities.EmployeeTaskOrder;
import com.il.sod.db.model.repositories.EmployeeTaskOrderRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeTaskOrderDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/employee-task-order")
@Produces(MediaType.APPLICATION_JSON)
// @Api(value = "/employee-task-order", tags = { "order" })
public class EmployeeTaskOrderService extends AbstractServiceMutations {
	@Autowired
	EmployeeTaskOrderRepository employeeTaskOrderRepository;

	@PUT
	@ApiOperation(value = "Create Service Type", response = EmployeeTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployeeTaskOrder(EmployeeTaskOrderDTO dto) throws SODAPIException {
		try {
			EmployeeTaskOrder entity = converter.map(dto, EmployeeTaskOrder.class);
			this.saveEntity(employeeTaskOrderRepository, entity);
			dto = converter.map(entity, EmployeeTaskOrderDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@POST
	@ApiOperation(value = "Update Service Type", response = EmployeeTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeTaskOrder(EmployeeTaskOrderDTO dto) throws SODAPIException {
		try {
			EmployeeTaskOrder entity = converter.map(dto, EmployeeTaskOrder.class);
			this.updateEntity(employeeTaskOrderRepository, entity);
			dto = converter.map(entity, EmployeeTaskOrderDTO.class);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@ApiOperation(value = "Create Service Type", response = EmployeeTaskOrderDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteEmployeeTaskOrder(EmployeeTaskOrderDTO dto) throws SODAPIException {
		try {
			EmployeeTaskOrder entity = converter.map(dto, EmployeeTaskOrder.class);
			this.deleteEntity(employeeTaskOrderRepository, entity.getIdEmployeeTaskOrder());
			return castEntityAsResponse(
					GeneralResponseMessage.getInstance().success().setMessage("Service deleted"),
					Response.Status.OK);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@GET
	@ApiOperation(value = "Get Service Type list", response = EmployeeTaskOrderDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeTaskOrderList() throws SODAPIException {
		List<EmployeeTaskOrder> rentityList = this.getEntityList(employeeTaskOrderRepository);
		List<EmployeeTaskOrderDTO> list = rentityList.stream().map((i) -> {
			EmployeeTaskOrderDTO dto = converter.map(i, EmployeeTaskOrderDTO.class);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

}
