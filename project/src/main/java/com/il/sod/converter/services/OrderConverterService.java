package com.il.sod.converter.services;

import com.il.sod.db.model.entities.Order;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.parse.UIServiceDTO;
import com.il.sod.rest.dto.parse.UITransportDTO;
import com.il.sod.rest.dto.serve.WPaymentInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderConverterService {
	
	@Autowired
	ServiceConverterService serviceConverterService;
	
	public OrderDTO convert(Order entity){
		OrderDTO result = OrderMapper.INSTANCE.map(entity);
		// add flattering...
		result.setClientName(result.getClient().getName() + " " + result.getClient().getLastName());
		result.setOrderTypeName(entity.getOrderType().getName());
		
		// override services to use the custom converter 
		Set<ServiceDTO> r = entity.getServices().stream().map(s -> serviceConverterService.convert(s)).collect(Collectors.toSet());
		result.setServices(r);
		
		if (result.getOrderTasks().size() > 0){
			int sumStatus = result.getOrderTasks().stream().mapToInt(ot -> ot.getStatus()).sum();
			double completed = ((sumStatus * 100) / result.getOrderTasks().size());
			result.setCompleted(completed);
		}else{
			result.setCompleted(0);	
		}
		return result;
	}
	
	public UIOrderDTO convert2UI(Order entity){
		if (entity != null){
			UIOrderDTO result = new UIOrderDTO();
			result.setIdOrder(entity.getId());
			result.setIdClient(entity.getClient().getId());
			result.setComments(entity.getComments());

			// TODO Validate transport for NPE
			List<UITransportDTO> lTransport = new ArrayList<>();
			lTransport.add(new UITransportDTO(entity.getIdAddressPickup(), entity.getPickUpDate(), 0, 0));
			lTransport.add(new UITransportDTO(entity.getIdAddressDeliver(), entity.getDeliverDate(), 1, 0));
			result.setTransport(lTransport);

			result.setTotal(entity.getTotal());
			result.setTotalServices(entity.getTotalServices());
			WPaymentInfoDTO paymentInfo = new WPaymentInfoDTO();
			if (entity.getPaymentInfo() != null){
				paymentInfo.setType(entity.getPaymentInfo().getType());
			}
			if (entity.getPaymentInfo() != null){
				paymentInfo.setTransactionInfo(entity.getPaymentInfo().getTransactionInfo());
			}

			result.setPaymentInfo(paymentInfo);
			List<UIServiceDTO> services = entity.getServices().stream().map(s -> {return serviceConverterService.convert2UI(s);}).collect(Collectors.toList());
			result.setServices(services);
			return result;
		}else{
			return null;
		}

	}

//	public OrderTypeDTO convert(OrderType input){
//		OrderTypeDTO restult = OrderMapper.INSTANCE.map(input);
//		return null;
//	}

}

