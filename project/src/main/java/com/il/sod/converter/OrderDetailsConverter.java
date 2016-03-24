package com.il.sod.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.mapper.SpecsMapper;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.dto.db.SpecDTO;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.web.OrderDetailsDTO;
import com.il.sod.rest.dto.web.ServiceDetailsDTO;

public class OrderDetailsConverter {
	
	public static void getOrderDetailFromOrderType(OrderType ot, OrderDetailsDTO orderDetailsDTO) {
		orderDetailsDTO.setIdOrderType(ot.getIdOrderType());
		orderDetailsDTO.setDescription(ot.getDescription());
		orderDetailsDTO.setName(ot.getName());
	}

	public static void getServiceDtlListFromOrderType(OrderType ot) {
		for (ServiceType setviceType : ot.getServiceTypes()) {
			for (Service service : setviceType.getServices()) {
				// ServiceDetailsDTO details
				ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
				getServiceDetailsFromService(setviceType, service, serviceDetailsDTO);

				// ServiceDetailsDTO specs (pending products and possible
				// values)
				List<SpecDTO> specsInfoL = getSpecInfoListFromService(service);
				serviceDetailsDTO.setSpecs(specsInfoL);

				// ServiceDetailsDTO tasks
				List<TaskDTO> sdsTasks = getTaskListFromService(service);
				serviceDetailsDTO.setTasks(sdsTasks);
			}
		}
	}

	public static void getServiceDetailsFromService(ServiceType setviceType, Service service,
			ServiceDetailsDTO serviceDetailsDTO) {
		serviceDetailsDTO.setName(service.getName());
		serviceDetailsDTO.setDescription(service.getDescription());
		serviceDetailsDTO.setPrice(service.getPrice());
		serviceDetailsDTO.setIdServiceType(setviceType.getIdServiceType());
	}

	public static List<TaskDTO> getTaskListFromService(Service service) {
		List<TaskDTO> sdsTasks = new ArrayList<>();
		for (ServiceTask serviceTask : service.getServiceTasks()) {
			sdsTasks.add(TaskMapper.INSTANCE.map(serviceTask.getTask()));
		}
		return sdsTasks;
	}

	public static List<SpecDTO> getSpecInfoListFromService(Service service) {
		List<SpecDTO> specsInfoL = service.getServiceSpecs().stream()
				.map(item -> SpecsMapper.INSTANCE.map(item.getSpec()))
				.collect(Collectors.toList());
		
		return specsInfoL;
	}
}
