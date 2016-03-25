package com.il.sod.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.il.sod.config.Constants;
import com.il.sod.db.model.entities.OrderType;
import com.il.sod.db.model.entities.Product;
import com.il.sod.db.model.entities.Service;
import com.il.sod.db.model.entities.ServiceSpec;
import com.il.sod.db.model.entities.ServiceTask;
import com.il.sod.db.model.entities.ServiceType;
import com.il.sod.db.model.entities.Spec;
import com.il.sod.db.model.entities.SpecsValue;
import com.il.sod.db.model.repositories.ProductRepository;
import com.il.sod.mapper.TaskMapper;
import com.il.sod.rest.dto.KeyValue;
import com.il.sod.rest.dto.db.TaskDTO;
import com.il.sod.rest.dto.web.OrderDetailsDTO;
import com.il.sod.rest.dto.web.ServiceDetailsDTO;
import com.il.sod.rest.dto.web.SpecSubDTO;

public class OrderDetailsConverter {
	
	private ProductRepository repository;
	
	public OrderDetailsDTO getOrderDetailFromOrderType(OrderType ot) {
		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
		orderDetailsDTO.setIdOrderType(ot.getIdOrderType());
		orderDetailsDTO.setDescription(ot.getDescription());
		orderDetailsDTO.setName(ot.getName());
		return orderDetailsDTO;
	}

	public List<ServiceDetailsDTO> getServiceDtlListFromOrderType(OrderType ot) {
		List<ServiceDetailsDTO> list = new ArrayList<>();
		for (ServiceType setviceType : ot.getServiceTypes()) {
			for (Service service : setviceType.getServices()) {
				// ServiceDetailsDTO details
				ServiceDetailsDTO serviceDetailsDTO = getServiceDetailsFromService(setviceType, service);

				// ServiceDetailsDTO specs (pending products and possible values)
				List<SpecSubDTO> specsInfoL = convertSpec2SpecSub(service.getServiceSpecs());
				serviceDetailsDTO.setSpecs(specsInfoL);

				// ServiceDetailsDTO tasks
				List<TaskDTO> sdsTasks = convertServiceTask2TaskDTO(service.getServiceTasks());
				serviceDetailsDTO.setTasks(sdsTasks);
				
				list.add(serviceDetailsDTO);
			}
		}
		return list;
	}

	public ServiceDetailsDTO getServiceDetailsFromService(ServiceType setviceType, Service service) {
		ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
		serviceDetailsDTO.setName(service.getName());
		serviceDetailsDTO.setDescription(service.getDescription());
		serviceDetailsDTO.setPrice(service.getPrice());
		serviceDetailsDTO.setIdServiceType(setviceType.getIdServiceType());
		return serviceDetailsDTO;
	}

	public List<TaskDTO> convertServiceTask2TaskDTO(List<ServiceTask> list) {
		List<TaskDTO> sdsTasks = new ArrayList<>();
		for (ServiceTask serviceTask : list) {
			sdsTasks.add(TaskMapper.INSTANCE.map(serviceTask.getTask()));
		}
		return sdsTasks;
	}

	public List<SpecSubDTO> convertSpec2SpecSub(List<ServiceSpec> list) {
		List<SpecSubDTO> result = new ArrayList<>();
		for (ServiceSpec serviceSpec : list){
			result.add(convertSpec2SpecSub(serviceSpec));
		}
		return result;
	}

	private SpecSubDTO convertSpec2SpecSub(ServiceSpec serviceSpec) {
		Spec spec = serviceSpec.getSpec();
		SpecSubDTO specSub = new SpecSubDTO();
		specSub.setIdSpecs(spec.getIdSpecs());
		specSub.setName(spec.getName());
		Map<Integer, List<KeyValue>> options = new HashMap<Integer, List<KeyValue>>();
		for (SpecsValue specValue : spec.getSpecsValues()){
			if (options.get(specValue.getSpec().getId()) == null){
				options.put(specValue.getSpec().getId(), new ArrayList<>());
			}
			KeyValue kv = new KeyValue();
			if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
				List<Product> listProduct = repository.findByIdProductType(specValue.getIdProductType());
				for (Product p : listProduct){
					kv = new KeyValue();
					kv.setKey(p.getId());
					kv.setValue(p.getName());
					options.get(specValue.getSpec().getId()).add(kv);
				}
				// get all products  by product type....
			}else if (specValue.getType() == Constants.SPEC_TYPE_VALUES){
				kv.setKey(0);
				kv.setValue(specValue.getValue());
				options.get(specValue.getSpec().getId()).add(kv);
			}
		}
		return specSub;
	}

	public ProductRepository getRepository() {
		return repository;
	}

	public void setRepository(ProductRepository repository) {
		this.repository = repository;
	}
}
