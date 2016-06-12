package com.il.sod.rest.api.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Component
@RolesAllowed("ADMIN")
@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employee", tags = { "employee" })
public class EmployeeService extends AbstractServiceMutations {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	private IEmployeeDAO employeeDAO;

	@POST
	@ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response saveEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.saveEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@Deprecated
	@PUT
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployee(EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.updateEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response updateEmployeeById(@PathParam("id") String id, EmployeeDTO dto) throws SODAPIException {
		try {
			Employee entity = EmployeeMapper.INSTANCE.map(dto);
			this.updateEntity(employeeRepository, entity);
			dto = EmployeeMapper.INSTANCE.map(entity);
			return castEntityAsResponse(dto, Response.Status.CREATED);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Delete Task Type", response = GeneralResponseMessage.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response deleteClient(@PathParam("id") String id) throws SODAPIException {
		Employee entity = employeeRepository.findOne(Integer.valueOf(id));
		if (entity == null){
			throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
		}
		this.deleteEntity(employeeRepository, entity.getId());
		return castEntityAsResponse(GeneralResponseMessage.getInstance().success().setMessage("Item deleted"),
				Response.Status.OK);
	}

	@GET
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class, responseContainer = "List")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeList() throws SODAPIException {
		List<Employee> entityList = this.getEntityList(employeeRepository);
		List<EmployeeDTO> list = entityList.stream().map((i) -> {
			EmployeeDTO dto = EmployeeMapper.INSTANCE.map(i);
			return dto;
		}).collect(Collectors.toList());
		return castEntityAsResponse(list);
	}

	@GET
	@Path("/{idEmployee}")
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeById(@PathParam("idEmployee") String idEmployee) throws SODAPIException {
		Employee employee = this.getEntity(employeeRepository, Integer.valueOf(idEmployee));
		if (employee == null ) {
			throw new SODAPIException(Response.Status.NO_CONTENT, "No employee found");
		}
		EmployeeDTO dto = EmployeeMapper.INSTANCE.map(employee);
		return castEntityAsResponse(dto);
	}

	@GET
	@Path("/email/{email}")
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class)
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "4## errors: Invalid input supplied", response = GeneralResponseMessage.class),
			@ApiResponse(code = 500, message = "5## errors: Server error", response = GeneralResponseMessage.class) })
	public Response getEmployeeByEmail(@PathParam("email") String email) throws SODAPIException {

		EmployeeDTO dto = new EmployeeDTO();
		List<Employee> Employees = employeeDAO.findByEmail(email);
		if (Employees != null && !Employees.isEmpty()) {
			Employee Employee = Employees.get(0);
			dto = EmployeeMapper.INSTANCE.map(Employee);
		}else{
			throw new SODAPIException(Response.Status.NO_CONTENT, "No employee found");
		}

		return castEntityAsResponse(dto);
	}


}
