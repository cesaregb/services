package com.il.sod.converter;

public class OrderDetailsConverter {
	
//	private SupplyRepository repository;
//	
//	public OrderDetailsDTO getOrderDetailFromOrderType(OrderType ot) {
//		OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
//		orderDetailsDTO.setIdOrderType(ot.getIdOrderType());
//		orderDetailsDTO.setDescription(ot.getDescription());
//		orderDetailsDTO.setName(ot.getName());
//		return orderDetailsDTO;
//	}
//
//	public List<ServiceDetailsDTO> getServiceDtlListFromOrderType(OrderType ot) {
//		List<ServiceDetailsDTO> list = new ArrayList<>();
//		for (ServiceType setviceType : ot.getServiceTypes()) {
//			for (Service service : setviceType.getServices()) {
//				// ServiceDetailsDTO details
//				ServiceDetailsDTO serviceDetailsDTO = getServiceDetailsFromService(setviceType, service);
//
//				// ServiceDetailsDTO specs (pending supplies and possible values)
//				List<SpecSubDTO> specsInfoL = convertSpec2SpecSub(service.getServiceSpecs());
//				serviceDetailsDTO.setSpecs(specsInfoL);
//
//				// ServiceDetailsDTO tasks
//				List<TaskDTO> sdsTasks = convertServiceTask2TaskDTO(service.getServiceTasks());
//				serviceDetailsDTO.setTasks(sdsTasks);
//				
//				list.add(serviceDetailsDTO);
//			}
//		}
//		return list;
//	}
//
//	public ServiceDetailsDTO getServiceDetailsFromService(ServiceType setviceType, Service service) {
//		ServiceDetailsDTO serviceDetailsDTO = new ServiceDetailsDTO();
//		serviceDetailsDTO.setName(service.getName());
//		serviceDetailsDTO.setDescription(service.getDescription());
//		serviceDetailsDTO.setPrice(service.getPrice());
//		serviceDetailsDTO.setIdServiceType(setviceType.getIdServiceType());
//		return serviceDetailsDTO;
//	}
//
//	public List<TaskDTO> convertServiceTask2TaskDTO(List<ServiceTask> list) {
//		List<TaskDTO> sdsTasks = new ArrayList<>();
//		for (ServiceTask serviceTask : list) {
//			sdsTasks.add(TaskMapper.INSTANCE.map(serviceTask.getTask()));
//		}
//		return sdsTasks;
//	}
//
//	public List<SpecSubDTO> convertSpec2SpecSub(List<ServiceSpec> list) {
//		List<SpecSubDTO> result = new ArrayList<>();
//		for (ServiceSpec serviceSpec : list){
//			result.add(convertSpec2SpecSub(serviceSpec));
//		}
//		return result;
//	}
//
//	private SpecSubDTO convertSpec2SpecSub(ServiceSpec serviceSpec) {
//		Spec spec = serviceSpec.getSpec();
//		SpecSubDTO specSub = new SpecSubDTO();
//		specSub.setIdSpecs(spec.getIdSpecs());
//		specSub.setName(spec.getName());
//		Map<Integer, List<KeyValue>> options = new HashMap<Integer, List<KeyValue>>();
//		for (SpecsValue specValue : spec.getSpecsValues()){
//			if (options.get(specValue.getSpec().getId()) == null){
//				options.put(specValue.getSpec().getId(), new ArrayList<>());
//			}
//			KeyValue kv = new KeyValue();
//			if (specValue.getType() == Constants.SPEC_TYPE_PRODUCT){
//				List<Supply> listSupply = repository.findByIdSupplyType(specValue.getIdSupplyType());
//				for (Supply p : listSupply){
//					kv = new KeyValue();
//					kv.setKey(p.getId());
//					kv.setValue(p.getName());
//					options.get(specValue.getSpec().getId()).add(kv);
//				}
//				// get all supplies  by supply type....
//			}else if (specValue.getType() == Constants.SPEC_TYPE_VALUES){
//				kv.setKey(0);
//				kv.setValue(specValue.getValue());
//				options.get(specValue.getSpec().getId()).add(kv);
//			}
//		}
//		return specSub;
//	}
//
//	public SupplyRepository getRepository() {
//		return repository;
//	}
//
//	public void setRepository(SupplyRepository repository) {
//		this.repository = repository;
//	}
}
