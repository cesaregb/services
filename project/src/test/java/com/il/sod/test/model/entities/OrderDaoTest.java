package com.il.sod.test.model.entities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.converter.OrderDetailsConverter;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.repositories.OrderTypeRepository;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderTypeDTO;
import com.il.sod.rest.dto.web.OrderDetailsDTO;
import com.il.sod.test.config.SpringTestConfiguration;

@Ignore
public class OrderDaoTest extends SpringTestConfiguration{
	
	@Autowired
	OrderTypeRepository orderTypeRepository;
	
	@Autowired
	ProductRepository productRepository;
	
    @Test
    public void test(){
    	List<OrderDetailsDTO> result = new ArrayList<>();
		OrderDetailsConverter converter = new OrderDetailsConverter();
		converter.setRepository(productRepository);
		
		// get all orderTypes.. 
		List<OrderType> orderTypeList = orderTypeRepository.findAll();
		
		for (OrderType ot : orderTypeList){
			System.out.println("*****************");
			System.out.println("services: " + ot.getServiceTypes().size());
			OrderTypeDTO dto = OrderMapper.INSTANCE.map(ot);
			System.out.println("services 2: " + dto.getServiceTypes().size());
			System.out.println("*****************");

//			OrderDetailsDTO orderDetailsDTO = converter.getOrderDetailFromOrderType(ot);
//
//			List<TaskDTO> taskList = new ArrayList<>();
//			for (OrderTypeTask ott : ot.getOrderTypeTasks()){
//				taskList.add(TaskMapper.INSTANCE.map(ott.getTask()));
//			}
//			orderDetailsDTO.setTasks(taskList);
//			
//			List<ServiceDetailsDTO> services = converter.getServiceDtlListFromOrderType(ot);
//			orderDetailsDTO.setServices(services);
//			result.add(orderDetailsDTO);
		}
    }
}