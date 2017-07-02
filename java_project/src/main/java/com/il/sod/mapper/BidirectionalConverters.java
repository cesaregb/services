package com.il.sod.mapper;

import com.il.sod.db.model.entities.*;
import com.il.sod.rest.dto.db.*;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BidirectionalConverters {
}

//*******************
// Orders Mappers
//*******************
class OrderSetConverter extends BidirectionalConverter<Set<Order>, Set<Integer>> {
  @Override
  public Set<Order> convertFrom(Set<Integer> source, Type<Set<Order>> destT) {
//		return source.stream().map(p -> (new Order()).setId(p)).collect(Collectors.toSet());
    return null;
  }

  @Override
  public Set<Integer> convertTo(Set<Order> source, Type<Set<Integer>> destT) {
    return source.stream().map(Order::getId).collect(Collectors.toSet());
  }
}

class OrderTaskSetConverter extends BidirectionalConverter<Set<OrderTask>, Set<OrderTaskDTO>> {
  @Override
  public Set<OrderTask> convertFrom(Set<OrderTaskDTO> source, Type<Set<OrderTask>> arg1) {
    return source.stream().map(OrderMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<OrderTaskDTO> convertTo(Set<OrderTask> source, Type<Set<OrderTaskDTO>> arg1) {
    return source.stream().map(OrderMapper.INSTANCE::map).collect(Collectors.toSet());
  }
}

class OrderTypeTasketConverter extends BidirectionalConverter<Set<OrderTypeTask>, List<OrderTypeTaskDTO>> {

  @Override
  public List<OrderTypeTaskDTO> convertTo(Set<OrderTypeTask> source, Type<List<OrderTypeTaskDTO>> type) {
    List<OrderTypeTaskDTO> result = source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toList());
    Collections.sort(result, (o1, o2) -> o1.getSortingOrder() - o2.getSortingOrder());
    return result;
  }

  @Override
  public Set<OrderTypeTask> convertFrom(List<OrderTypeTaskDTO> source, Type<Set<OrderTypeTask>> type) {
    return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

}

class ClientConverter extends BidirectionalConverter<Client, ClientDTO> {
  @Override
  public Client convertFrom(ClientDTO source, Type<Client> arg1) {
    return ClientMapper.INSTANCE.map(source);
  }

  @Override
  public ClientDTO convertTo(Client source, Type<ClientDTO> arg1) {
    return ClientMapper.INSTANCE.map(source);
  }
}

class AssetTaskOrderSetConverter extends BidirectionalConverter<Set<AssetTaskOrder>, Set<AssetTaskOrderDTO>> {
  @Override
  public Set<AssetTaskOrder> convertFrom(Set<AssetTaskOrderDTO> source, Type<Set<AssetTaskOrder>> arg1) {
    return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<AssetTaskOrderDTO> convertTo(Set<AssetTaskOrder> source, Type<Set<AssetTaskOrderDTO>> arg1) {
    return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class EmployeeTaskOrderSetConverter
        extends BidirectionalConverter<Set<EmployeeTaskOrder>, Set<EmployeeTaskOrderDTO>> {
  @Override
  public Set<EmployeeTaskOrder> convertFrom(Set<EmployeeTaskOrderDTO> source, Type<Set<EmployeeTaskOrder>> arg1) {
    return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<EmployeeTaskOrderDTO> convertTo(Set<EmployeeTaskOrder> source, Type<Set<EmployeeTaskOrderDTO>> arg1) {
    return source.stream().map(item -> OrderMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class ServiceTypeTaskSetConverter extends BidirectionalConverter<Set<ServiceTypeTask>, List<ServiceTypeTaskDTO>> {

  @Override
  public List<ServiceTypeTaskDTO> convertTo(Set<ServiceTypeTask> source, Type<List<ServiceTypeTaskDTO>> type) {
    List<ServiceTypeTaskDTO> result = source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toList());
    Collections.sort(result, Comparator.comparingInt(ServiceTypeTaskDTO::getSortingOrder));
    return result;
  }

  @Override
  public Set<ServiceTypeTask> convertFrom(List<ServiceTypeTaskDTO> source, Type<Set<ServiceTypeTask>> type) {
    return source.stream().map(p -> (ServiceMapper.INSTANCE.map(p))).collect(Collectors.toSet());
  }
}


class ServiceSpecSetConverter extends BidirectionalConverter<Set<ServiceSpec>, Set<ServiceSpecDTO>> {
  @Override
  public Set<ServiceSpec> convertFrom(Set<ServiceSpecDTO> source, Type<Set<ServiceSpec>> arg1) {
    return source.stream().map(p -> (new ServiceSpec()).setId(p.getIdServiceSpecs())).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceSpecDTO> convertTo(Set<ServiceSpec> source, Type<Set<ServiceSpecDTO>> arg1) {
    return source.stream().map(p -> SpecsMapper.INSTANCE.map(p)).collect(Collectors.toSet());
  }
}

//class ServiceTaskSet2IntConverter extends BidirectionalConverter<Set<ServiceTask>, Set<Integer>> {
//	@Override
//	public Set<ServiceTask> convertFrom(Set<Integer> source, Type<Set<ServiceTask>> arg1) {
//		return source.stream().map(p -> (new ServiceTask()).setId(p)).collect(Collectors.toSet());
//	}
//
//	@Override
//	public Set<Integer> convertTo(Set<ServiceTask> source, Type<Set<Integer>> arg1) {
//		return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
//	}
//}

class ServiceTaskSetConverter extends BidirectionalConverter<Set<ServiceTask>, Set<ServiceTaskDTO>> {
  @Override
  public Set<ServiceTask> convertFrom(Set<ServiceTaskDTO> source, Type<Set<ServiceTask>> arg1) {
    return source.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceTaskDTO> convertTo(Set<ServiceTask> source, Type<Set<ServiceTaskDTO>> arg1) {
    return source.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
  }
}

class AssetTaskServiceSetConverter extends BidirectionalConverter<Set<AssetTaskService>, Set<AssetTaskServiceDTO>> {

  @Override
  public Set<AssetTaskService> convertFrom(Set<AssetTaskServiceDTO> source, Type<Set<AssetTaskService>> arg1) {
    return source.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<AssetTaskServiceDTO> convertTo(Set<AssetTaskService> source, Type<Set<AssetTaskServiceDTO>> arg1) {
    return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class EmployeeTaskServiceSetConverter extends BidirectionalConverter<Set<EmployeeTaskService>, Set<EmployeeTaskServiceDTO>> {
  @Override
  public Set<EmployeeTaskService> convertFrom(Set<EmployeeTaskServiceDTO> source, Type<Set<EmployeeTaskService>> arg1) {
    return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<EmployeeTaskServiceDTO> convertTo(Set<EmployeeTaskService> source,
                                               Type<Set<EmployeeTaskServiceDTO>> arg1) {
    return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class OrderTypeSetConverter extends BidirectionalConverter<Set<OrderType>, Set<Integer>> {
  @Override
  public Set<OrderType> convertFrom(Set<Integer> source, Type<Set<OrderType>> arg1) {
    return source.stream().map(p -> (new OrderType()).setId(p)).collect(Collectors.toSet());
  }

  @Override
  public Set<Integer> convertTo(Set<OrderType> source, Type<Set<Integer>> arg1) {
    return source.stream().map(OrderType::getId).collect(Collectors.toSet());
  }
}

class ServiceTypeSetConverter extends BidirectionalConverter<Set<ServiceType>, Set<ServiceTypeDTO>> {
  @Override
  public Set<ServiceType> convertFrom(Set<ServiceTypeDTO> source, Type<Set<ServiceType>> arg1) {
    return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceTypeDTO> convertTo(Set<ServiceType> source, Type<Set<ServiceTypeDTO>> arg1) {
    return source.stream().map(item -> ServiceMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class ServiceTypeSet2IntConverter extends BidirectionalConverter<Set<ServiceType>, Set<Integer>> {
  @Override
  public Set<Integer> convertTo(Set<ServiceType> source, Type<Set<Integer>> type) {
    return source.stream().map(ServiceType::getId).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceType> convertFrom(Set<Integer> source, Type<Set<ServiceType>> type) {
    return source.stream().map(p -> (new ServiceType()).setId(p)).collect(Collectors.toSet());
  }
}

//class SpecConverter extends BidirectionalConverter<Spec, SpecDTO> {
//	@Override
//	public Spec convertFrom(SpecDTO source, Type<Spec> arg1) {
//		return SpecsMapper.INSTANCE.map(source);
//	}
//
//	@Override
//	public SpecDTO convertTo(Spec source, Type<SpecDTO> arg1) {
//		return SpecsMapper.INSTANCE.map(source);
//	}
//}

class SpecSetConverter extends BidirectionalConverter<Set<Spec>, Set<SpecDTO>> {
  @Override
  public Set<SpecDTO> convertTo(Set<Spec> source, Type<Set<SpecDTO>> type) {
    return source.stream().map(SpecsMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<Spec> convertFrom(Set<SpecDTO> source, Type<Set<Spec>> type) {
    return source.stream().map(SpecsMapper.INSTANCE::map).collect(Collectors.toSet());
  }
}

//class TaskConverter extends BidirectionalConverter<Task, TaskDTO> {
//	@Override
//	public Task convertFrom(TaskDTO arg0, Type<Task> arg1) {
//		return TaskMapper.INSTANCE.map(arg0);
//	}
//	@Override
//	public TaskDTO convertTo(Task arg0, Type<TaskDTO> arg1) {
//		return TaskMapper.INSTANCE.map(arg0);
//	}
//}

class ServiceSet2IntConverter extends BidirectionalConverter<Set<Service>, Set<Integer>> {

  @Override
  public Set<Service> convertFrom(Set<Integer> source, Type<Set<Service>> arg1) {
    return source.stream().map(p -> (new Service()).setId(p)).collect(Collectors.toSet());
  }

  @Override
  public Set<Integer> convertTo(Set<Service> source, Type<Set<Integer>> arg1) {
    return source.stream().map(p -> p.getId()).collect(Collectors.toSet());
  }
}

class ServiceSetConverter extends BidirectionalConverter<Set<Service>, Set<ServiceDTO>> {

  @Override
  public Set<Service> convertFrom(Set<ServiceDTO> source, Type<Set<Service>> arg1) {
    return source.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceDTO> convertTo(Set<Service> source, Type<Set<ServiceDTO>> arg1) {
    return source.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toSet());
  }
}

class ProductTypeSetConverter extends BidirectionalConverter<Set<ProductType>, Set<ProductTypeDTO>> {
  @Override
  public Set<ProductTypeDTO> convertTo(Set<ProductType> source, Type<Set<ProductTypeDTO>> type) {
    return source.stream().map(ProductMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<ProductType> convertFrom(Set<ProductTypeDTO> source, Type<Set<ProductType>> type) {
    return source.stream().map(ProductMapper.INSTANCE::map).collect(Collectors.toSet());
  }
}

class ServiceProductDTOSetConverter extends BidirectionalConverter<Set<ServiceProduct>, Set<ServiceProductDTO>> {
  @Override
  public Set<ServiceProductDTO> convertTo(Set<ServiceProduct> source, Type<Set<ServiceProductDTO>> type) {
    return source.stream().map(ProductMapper.INSTANCE::map).collect(Collectors.toSet());
  }

  @Override
  public Set<ServiceProduct> convertFrom(Set<ServiceProductDTO> source, Type<Set<ServiceProduct>> type) {
    return null;
  }
}


// *******************
// ****** Clients mapper
// *******************
class ClientDTOConverter extends BidirectionalConverter<Set<Client>, Set<ClientDTO>> {

  @Override
  public Set<ClientDTO> convertTo(Set<Client> source, Type<Set<ClientDTO>> type) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<Client> convertFrom(Set<ClientDTO> source, Type<Set<Client>> type) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class AddressDTOConverter extends BidirectionalConverter<Set<Address>, Set<AddressDTO>> {
  @Override
  public Set<Address> convertFrom(Set<AddressDTO> source, Type<Set<Address>> arg1) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<AddressDTO> convertTo(Set<Address> source, Type<Set<AddressDTO>> arg1) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

class ClientBagSetConverter extends BidirectionalConverter<Set<ClientBag>, Set<ClientBagDTO>> {

  @Override
  public Set<ClientBagDTO> convertTo(Set<ClientBag> source, Type<Set<ClientBagDTO>> type) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<ClientBag> convertFrom(Set<ClientBagDTO> source, Type<Set<ClientBag>> type) {
    return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

//class ClientPaymentInfoConverter extends BidirectionalConverter<Set<ClientPaymentInfo>, Set<ClientPaymentInfoDTO>> {
//	@Override
//	public Set<ClientPaymentInfo> convertFrom(Set<ClientPaymentInfoDTO> source, Type<Set<ClientPaymentInfo>> arg1) {
//		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
//	}
//
//	@Override
//	public Set<ClientPaymentInfoDTO> convertTo(Set<ClientPaymentInfo> source, Type<Set<ClientPaymentInfoDTO>> arg1) {
//		return source.stream().map(item -> ClientMapper.INSTANCE.map(item)).collect(Collectors.toSet());
//	}
//}

class ClientPaymentInfoSetConverter extends BidirectionalConverter<Set<ClientPaymentInfo>, Set<ClientPaymentInfoDTO>> {
  @Override
  public Set<ClientPaymentInfo> convertFrom(Set<ClientPaymentInfoDTO> source, Type<Set<ClientPaymentInfo>> arg1) {
    return source.stream().map(item -> PaymentMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<ClientPaymentInfoDTO> convertTo(Set<ClientPaymentInfo> source, Type<Set<ClientPaymentInfoDTO>> arg1) {
    return source.stream().map(item -> PaymentMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

/* Products */

class ProductSetConverter extends BidirectionalConverter<Set<Product>, Set<ProductDTO>> {
  @Override
  public Set<Product> convertFrom(Set<ProductDTO> source, Type<Set<Product>> arg1) {
    return source.stream().map(item -> ProductMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<ProductDTO> convertTo(Set<Product> source, Type<Set<ProductDTO>> arg1) {
    return source.stream().map(item -> ProductMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}
/* Store */

class DistanceInfoSetConverter extends BidirectionalConverter<Set<DistanceInfo>, Set<DistanceInfoDTO>> {
  @Override
  public Set<DistanceInfoDTO> convertTo(Set<DistanceInfo> source, Type<Set<DistanceInfoDTO>> type) {
    return source.stream().map(item -> StoreInfoMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<DistanceInfo> convertFrom(Set<DistanceInfoDTO> source, Type<Set<DistanceInfo>> type) {
    return source.stream().map(item -> StoreInfoMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }
}

/* PriceAdjustment */

class PriceAdjustmentSetConverter extends BidirectionalConverter<Set<PriceAdjustment>, Set<PriceAdjustmentDTO>> {
  @Override
  public Set<PriceAdjustmentDTO> convertTo(Set<PriceAdjustment> source, Type<Set<PriceAdjustmentDTO>> type) {
    return source.stream().map(item -> PriceAdjustmentMapper.INSTANCE.map(item)).collect(Collectors.toSet());
  }

  @Override
  public Set<PriceAdjustment> convertFrom(Set<PriceAdjustmentDTO> priceAdjustmentDTOs, Type<Set<PriceAdjustment>> type) {
    return null;
  }
}

