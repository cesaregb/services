package com.il.sod.rest.api.model;

import com.il.sod.db.dao.IEmployeeDAO;
import com.il.sod.db.model.entities.Employee;
import com.il.sod.db.model.repositories.EmployeeRepository;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.EmployeeMapper;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.EmployeeDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/employees")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/employees", tags = {"employees"})
public class EmployeeService extends AbstractServiceMutations {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private IEmployeeDAO employeeDAO;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Employee", response = EmployeeDTO.class)
	public ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO dto) throws SODAPIException {
		Employee entity = EmployeeMapper.INSTANCE.map(dto);
		this.saveEntity(employeeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Employee", response = EmployeeDTO.class)
	public ResponseEntity<EmployeeDTO> updateEmployee(EmployeeDTO dto) throws SODAPIException {
		Employee entity = EmployeeMapper.INSTANCE.map(dto);
		this.updateEntity(employeeRepository, entity);
		dto = EmployeeMapper.INSTANCE.map(entity);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ApiOperation(value = "Delete Item", response = GeneralResponseMessage.class)
	public ResponseEntity<GeneralResponseMessage> deleteItem(@PathVariable("id") String id) throws SODAPIException {
		Employee entity = employeeRepository.findOne(Integer.valueOf(id));
		if (entity == null) {
			throw new SODAPIException(HttpStatus.BAD_REQUEST, "Item not found");
		}
		this.softDeleteEntity(employeeRepository, entity.getId());
		return new ResponseEntity<>(new GeneralResponseMessage(true, "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class, responseContainer = "List")
	public ResponseEntity<List<EmployeeDTO>> getEmployeeList(@QueryParam("email") String email) throws SODAPIException {
		List<Employee> entityList = null;
		if (!StringUtils.isEmpty(email)) {
			entityList = employeeDAO.findByEmail(email);
		} else {
			entityList = this.getEntityList(employeeRepository);
		}
		List<EmployeeDTO> list = entityList.stream().map(EmployeeMapper.INSTANCE::map)
				.filter(DeletablePredicate.isActive())
				.collect(Collectors.toList());
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{idEmployee}")
	@ApiOperation(value = "Get Employee list", response = EmployeeDTO.class)
	public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("idEmployee") String idEmployee) throws SODAPIException {
		Employee employee = this.getEntity(employeeRepository, Integer.valueOf(idEmployee));
		if (employee == null) {
			throw new SODAPIException(HttpStatus.NO_CONTENT, "No employee found");
		}
		EmployeeDTO dto = EmployeeMapper.INSTANCE.map(employee);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}
