package com.il.sod.services.cruds;

import com.il.sod.config.Constants;
import com.il.sod.converter.services.OrderConverterService;
import com.il.sod.converter.services.SpecificObjectsConverterService;
import com.il.sod.db.dao.impl.OrdersDAO;
import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.OrderMapper;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.dto.db.OrderDTO;
import com.il.sod.rest.dto.db.OrderTaskDTO;
import com.il.sod.rest.dto.db.ServiceDTO;
import com.il.sod.rest.dto.db.ServiceTaskDTO;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.parse.UIProductDTO;
import com.il.sod.rest.dto.parse.UIServiceDTO;
import com.il.sod.rest.dto.parse.UISpecDTO;
import com.il.sod.rest.dto.serve.WServiceCategoryDTO;
import com.il.sod.rest.dto.serve.WServiceTypeDTO;
import com.il.sod.rest.dto.specifics.OrderTasksInfoDTO;
import com.il.sod.rest.dto.specifics.ServiceTasksInfoDTO;
import com.il.sod.services.utils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class OrdersSv extends EntityServicesBase {

  private final static Logger LOGGER = getLogger(OrdersSv.class);

  @Autowired
  OrderRepository orderRepository;

  @Autowired
  OrdersDAO ordersDAO;

  @Autowired
  OrderConverterService orderConverterService;

  @Autowired
  ClientRepository clientRepository;

  @Autowired
  CashOutSv cashOutSv;

  @Autowired
  OrderTypeRepository orderTypeRepository;

  @Autowired
  ServiceTypeRepository serviceTypeRepository;

  @Autowired
  SpecRepository specRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  ServiceCategoryRepository serviceCategoryRepository;

  @Autowired
  SpecificObjectsConverterService specificObjectsConverterService;

  @Autowired
  ServiceRepository serviceRepository;

  public OrderDTO updateOrder(OrderDTO dto) throws SODAPIException {
    Order entity = orderRepository.findOne(dto.getIdOrder());
    entity = OrderMapper.INSTANCE.map(dto, entity);
    this.updateEntity(orderRepository, entity);
    return OrderMapper.INSTANCE.map(entity);
  }

  public boolean deleteOrder(OrderDTO dto) throws SODAPIException {
    Order entity = OrderMapper.INSTANCE.map(dto);
    this.deleteEntity(orderRepository, entity.getIdOrder());
    return true;
  }

  public List<OrderDTO> getOrderList() throws SODAPIException {
    List<Order> entityList = this.getEntityList(orderRepository);
    return entityList.stream().map((i) -> {
      OrderDTO dto = orderConverterService.convert(i);
      return dto;
    }).collect(Collectors.toList());
  }

  public List<OrderDTO> getOrdersByStatus(int status) throws SODAPIException {
    return ordersDAO.findByStatus(status)
            .stream()
            .map(o -> orderConverterService.convert(o))
            .collect(Collectors.toList());
  }

  public OrderDTO getOrderById(String orderId){
    OrderDTO order = OrderMapper.INSTANCE.map(this.getEntity(orderRepository, Integer.valueOf(orderId)));
    Set<ServiceDTO> services = serviceRepository.findByOrder(order.getIdOrder()).stream()
            .map(ServiceMapper.INSTANCE::map)
            .collect(Collectors.toSet());
    order.setServices(services);
    return order;
  }

  public OrderTasksInfoDTO getOrderTaskInfo(int orderId) {
    Order order = orderRepository.findOne(orderId);
    OrderTasksInfoDTO result = new OrderTasksInfoDTO();
    result.setPaymentStatus(order.isPaymentStatus());
    result.setIdOrder(orderId);
    result.setIdClient(order.getClient().getId());
    result.setClientName(order.getClient().getFullName());
    result.setOrderTypeName(order.getOrderType().getName());

    Set<OrderTaskDTO> orderTasks = order.getOrderTasks().stream()
            .map(OrderMapper.INSTANCE::map)
            .collect(Collectors.toSet());

    result.setOrderTasks(orderTasks);

    Set<ServiceTasksInfoDTO> services = order.getServices()
            .stream()
            .map(this::getServiceTasksInfoDTO)
            .collect(Collectors.toSet());

    result.setServices(services);
    return result;
  }

  private ServiceTasksInfoDTO getServiceTasksInfoDTO(com.il.sod.db.model.entities.Service service) {
    ServiceTasksInfoDTO serviceTI = new ServiceTasksInfoDTO();
    serviceTI.setName(service.getName());
    StringBuilder description = new StringBuilder();
    description.append(
            service.getServiceProducts().stream()
                    .filter(p -> p.getQuantity() > 0)
                    .map(sp -> {
                      StringBuilder sb = new StringBuilder(sp.getProduct().getName())
                              .append(" x ")
                              .append(sp.getQuantity())
                              .append("\n");
                      return sb.toString();
                    }).collect(Collectors.joining("\n"))
    );

    description.append(
            service.getServiceSpecs().stream()
                    .filter(p -> p.getQuantity() > 0)
                    .map(spec -> {
                      StringBuilder sb = new StringBuilder()
                              .append(spec.getSpec().getName())
                              .append(" tipo: ")
                              .append(spec.getSelectedValue())
                              .append(" x ")
                              .append(spec.getSpecPrice())
                              .append("\n");

                      return sb.toString();
                    }).collect(Collectors.joining("\n"))
    );

    serviceTI.setServiceDescription(description.toString());
    serviceTI.setIdService(service.getIdService());
    Set<ServiceTaskDTO> serviceTaskDTOS = service.getServiceTasks()
            .stream()
            .map(ServiceMapper.INSTANCE::map)
            .sorted(Comparator.comparingInt(ServiceTaskDTO::getIdTask))
            .collect(Collectors.toSet());
    serviceTI.setServiceTasks(serviceTaskDTOS);
    serviceTI.setIdOrder(service.getOrder().getId());
    return serviceTI;
  }

  public List<OrderDTO> getOrdersByClient(int idClient) throws SODAPIException {
    List<Order> entityList = orderRepository.findByClient(this.getEntity(clientRepository, idClient));
    return entityList.stream().map(o -> {
      OrderDTO odto = OrderMapper.INSTANCE.map(o);
      odto.setOrderTypeName(o.getOrderType().getName());
      return odto;
    }).collect(Collectors.toList());
  }

  public List<OrderDTO> getOrderNotCashedOut() throws SODAPIException {
    return cashOutSv.filterOrdersByPaymentStatus(ordersDAO.findOrderNotCashedOut())
            .stream()
            .filter(Order::isPaymentStatus)
            .map(orderConverterService::convert)
            .collect(Collectors.toList());
  }

  public List<OrderDTO> getOrderByDate(Date date) {
    return ordersDAO.getOrderByDate(date).stream()
            .map(orderConverterService::convertLite)
            .collect(Collectors.toList());
  }

  public List<OrderDTO> getOrderBetweenDates(Date initDate, Date endDate) {
    return ordersDAO.getOrderBetweenDates(initDate, endDate).stream()
            .map(orderConverterService::convertLite)
            .collect(Collectors.toList());
  }


  public List<WServiceCategoryDTO> getOrderTypes() throws SODAPIException {
    List<ServiceCategory> entities = serviceCategoryRepository.findAll();
    return entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());
  }

  public List<WServiceCategoryDTO> getOrderTypesPublic() throws SODAPIException {
    List<ServiceCategory> entities = serviceCategoryRepository.findAll();
    List<WServiceCategoryDTO> result = entities.stream().map(i -> specificObjectsConverterService.map(i)).collect(Collectors.toList());
    for (WServiceCategoryDTO cat : result) {
      Set<WServiceTypeDTO> serviceTypes = cat.getServiceTypes().stream().filter(WServiceTypeDTO::isCalculator).collect(Collectors.toSet());
      cat.setServiceTypes(serviceTypes);
    }
    return result;
  }


  public OrderDTO createOrder(UIOrderDTO orderInputDto) throws SODAPIException {
    OrderDTO result;
    Client client = clientRepository.findAllIncludeOrders(orderInputDto.getIdClient());
    if (client == null) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found {%d}", orderInputDto.getIdClient());
    }
    if (orderInputDto.getTransport() == null || orderInputDto.getTransport().isEmpty() || orderInputDto.getTransport().size() < 2) {
      int errorSize = (orderInputDto.getTransport() != null) ? orderInputDto.getTransport().size() : 0;
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Error [transport] object is not complete %s", errorSize);
    }

    int valOrderType = calculateOrderType(orderInputDto);
    OrderType orderType = orderTypeRepository.findOne(valOrderType);
    Order orderEntity = createBasicOrder(orderInputDto);
    orderEntity.setClient(client);
    orderEntity.setOrderType(orderType);
    addingOrderTasks(orderEntity, orderType);
    addingPaymentInfo(orderInputDto, orderEntity);
    addingServices(orderInputDto, orderEntity);

    orderEntity = orderRepository.save(orderEntity);
    result = OrderMapper.INSTANCE.map(orderEntity);
    LOGGER.info("Order Saved!");
    LOGGER.info("Object mapper, response: {}", ConvertUtils.castEntityAsString(result));

    return result;
  }

  private void addingServices(UIOrderDTO orderInputDto, Order orderEntity) {
    for (UIServiceDTO uiServiceDTO : orderInputDto.getServices()) {
      orderEntity.addService(createService(uiServiceDTO));
    }
  }

  @NotNull
  private com.il.sod.db.model.entities.Service createService(UIServiceDTO uiServiceDTO) {
    ServiceType serviceType = serviceTypeRepository.findOne(uiServiceDTO.getIdServiceType());
    com.il.sod.db.model.entities.Service service = createBasicService(uiServiceDTO, serviceType);
    addingServiceTasks(service, serviceType);
    addingServiceSpecs(uiServiceDTO, service);
    addingProducts(uiServiceDTO, service);
    return service;
  }

  @NotNull
  private com.il.sod.db.model.entities.Service createBasicService(UIServiceDTO uiServiceDTO, ServiceType serviceType) {
    com.il.sod.db.model.entities.Service service = new com.il.sod.db.model.entities.Service();
    service.setServiceType(serviceType);
    service.setName(serviceType.getName());
    service.setDescription(serviceType.getDescription());
    service.setPrice(uiServiceDTO.getPrice());
    service.setDescription(uiServiceDTO.getComments());
    return service;
  }

  private void addingProducts(UIServiceDTO uiServiceDTO, com.il.sod.db.model.entities.Service service) {
    if (!CollectionUtils.isEmpty(uiServiceDTO.getProducts())) {
      for (UIProductDTO product : uiServiceDTO.getProducts()) {
        ServiceProduct ss = new ServiceProduct();
        ss.setProduct(productRepository.findOne(product.getIdProduct()));
        ss.setQuantity(product.getQuantity());
        ss.setPrice(product.getPrice());
        service.addProduct(ss);
      }
    }
  }

  private void addingServiceSpecs(UIServiceDTO uiServiceDTO, com.il.sod.db.model.entities.Service service) {
    for (UISpecDTO specInput : uiServiceDTO.getSpecs()) {
      ServiceSpec serviceSpec = new ServiceSpec();
      serviceSpec.setQuantity(specInput.getQuantity());
      serviceSpec.setService(service);
      serviceSpec.setSpec(specRepository.findOne(specInput.getIdSpecs()));
      serviceSpec.setSelectedValue(specInput.getValue());
      serviceSpec.setSpecPrice(specInput.getPrice());
      // adding service spec
      service.addServiceSpec(serviceSpec);
    }
  }

  private void addingServiceTasks(com.il.sod.db.model.entities.Service service, ServiceType serviceType) {
    for (ServiceTypeTask serviceTypeTask : serviceType.getServiceTypeTasks()) {
      ServiceTask serviceTask = new ServiceTask();
      serviceTask.setTask(serviceTypeTask.getTask());
      serviceTask.setTime(serviceTypeTask.getTime());
      serviceTask.setSortingOrder(serviceTypeTask.getSortingOrder());
      serviceTask.setService(service);
      service.addServiceTask(serviceTask);
    }
  }

  private void addingPaymentInfo(UIOrderDTO orderInputDto, Order orderEntity) {
    if (orderInputDto.getPaymentInfo() != null && orderInputDto.getPaymentInfo().getTransactionInfo() != null) {
      PaymentInfo pi = new PaymentInfo();
      pi.setTransactionInfo(orderInputDto.getPaymentInfo().getTransactionInfo());
      pi.setOrder(orderEntity);
      pi.setType(orderInputDto.getPaymentInfo().getType());
      orderEntity.setPaymentInfo(pi);
    }
  }

  private void addingOrderTasks(Order orderEntity, OrderType orderType) {
    for (OrderTypeTask ott : orderType.getOrderTypeTask()) {
      OrderTask orderTask = new OrderTask();
      orderTask.setTask(ott.getTask());
      orderTask.setSortingOrder(ott.getSortingOrder());
      orderTask.setOrder(orderEntity);
      orderEntity.addOrderTask(orderTask);
    }
  }

  @NotNull
  private Order createBasicOrder(UIOrderDTO orderInputDto) throws SODAPIException {
    Order orderEntity = new Order();
    orderEntity.setPaymentStatus(orderInputDto.isPaymentStatus());
    orderEntity.setComments(orderInputDto.getComments());

    orderEntity.setIdAddressPickup(orderInputDto.getTransport().get(0).getIdAddress());
    orderEntity.setPickUpDate(orderInputDto.getTransport().get(0).getDate());
    orderEntity.setPickUpPrice(orderInputDto.getTransport().get(0).getPrice());

    orderEntity.setIdAddressDeliver(orderInputDto.getTransport().get(1).getIdAddress());
    orderEntity.setDeliverDate(orderInputDto.getTransport().get(1).getDate());
    orderEntity.setDeliverPrice(orderInputDto.getTransport().get(1).getPrice());

    orderEntity.setTotalServices(orderInputDto.getTotalServices());
    orderEntity.setTotal(orderInputDto.getTotal());
    orderEntity.setStatus(Constants.ORDER_CREATED);

    //TODO validate if discount is applicable!
    orderEntity.setDiscount(orderInputDto.getDiscount());
    return orderEntity;
  }


  private static int calculateOrderType(UIOrderDTO orderInputDto) {
    if (orderInputDto.getIdOrderType() >= 1 && orderInputDto.getIdOrderType() <= 4) {
      return orderInputDto.getIdOrderType();
    }
    int orderType;
    orderType = 4;
    int a = orderInputDto.getTransport().get(0).getIdAddress();
    int b = orderInputDto.getTransport().get(1).getIdAddress();
    if (a > 0 && b > 0) {
      orderType = 1;
    } else if (a > 0) {
      orderType = 2;
    } else if (b > 0) {
      orderType = 3;
    }
    return orderType;
  }

  /**
   * Orders that are not complete; status = 0 || paymentStatu = false
   *
   * @return
   */
  public List<OrderDTO> getPendingOrders() {
    return orderRepository.findAll()
            .stream()
            .filter(o -> o.getStatus() == 0 || !o.isPaymentStatus())
            .map(o -> orderConverterService.convert(o))
            .collect(Collectors.toList());
  }

}
