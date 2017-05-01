package com.il.sod.rest.api.model;

import com.il.sod.exception.SODAPIException;
import com.il.sod.rest.api.AbstractServiceMutations;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.services.cruds.OrdersSv;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RolesAllowed("ADMIN")
@RequestMapping(value = "/orders", produces = MediaType.APPLICATION_JSON)
@Api(value = "/orders", tags = {"orders"})
public class OrderService extends AbstractServiceMutations {

	@Autowired
	OrdersSv ordersSv;

	@RequestMapping(method = RequestMethod.POST)
	@ApiOperation(value = "Create Order Type", response = OrderDTO.class)
	public ResponseEntity<OrderDTO> saveOrder(OrderDTO dto) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.saveOrder(dto), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ApiOperation(value = "Update Order Type", response = OrderDTO.class)
	public ResponseEntity<OrderDTO> updateOrder(OrderDTO dto) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.updateOrder(dto), HttpStatus.OK);
	}

	//TODO by param
	@RequestMapping(method = RequestMethod.DELETE)
	@ApiOperation(value = "Create Order Type", response = OrderDTO.class)
	public ResponseEntity<GeneralResponseMessage> deleteOrder(OrderDTO dto) throws SODAPIException {
		return new ResponseEntity<>(new GeneralResponseMessage(ordersSv.deleteOrder(dto), "Entity deleted"),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get Order Type list", response = OrderDTO.class, responseContainer = "List")
	public ResponseEntity<List<OrderDTO>> getOrderList() throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrderList(), HttpStatus.OK);
	}

	//TODO change all this methods to be accesible thru the GET
	@RequestMapping(method = RequestMethod.GET, value = "/byStatus/{status}")
	@ApiOperation(value = "Get Order by status ", response = OrderDTO.class, responseContainer = "List")
	public ResponseEntity<List<OrderDTO>> getOrdersByStatus(@PathVariable("status") int status) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrdersByStatus(status), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byId/{orderId}")
	@ApiOperation(value = "Get Order by id", response = OrderDTO.class)
	public ResponseEntity<OrderDTO> getOrderById(@PathVariable("orderId") String orderId) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrderById(orderId), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/byClient/{idClient}")
	@ApiOperation(value = "Get Client Orders list", response = OrderDTO.class, responseContainer = "List")
	public ResponseEntity<List<OrderDTO>> getOrdersByClient(@PathVariable("idClient") int idClient) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrdersByClient(idClient), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/tasks/{orderId}")
	@ApiOperation(value = "Get Task list for order", response = OrderTasksInfoDTO.class)
	public ResponseEntity<OrderTasksInfoDTO> getOrderTaskInfo(@PathVariable("orderId") int orderId) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrderTaskInfo(orderId), HttpStatus.OK);
	}

	@Deprecated
	@RequestMapping(method = RequestMethod.GET, value = "/forEdit/{orderId}")
	@ApiOperation(value = "Get Order in Edit object mode.", response = OrderTasksInfoDTO.class)
	public ResponseEntity<UIOrderDTO> getOrder4Edit(@PathVariable("orderId") String orderId) throws SODAPIException {
		return new ResponseEntity<>(ordersSv.getOrder4Edit(orderId), HttpStatus.OK);
	}
}
