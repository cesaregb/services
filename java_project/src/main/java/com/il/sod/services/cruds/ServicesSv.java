package com.il.sod.services.cruds;

import com.il.sod.db.model.entities.*;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ServiceMapper;
import com.il.sod.rest.dto.db.ProductTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeDTO;
import com.il.sod.rest.dto.db.ServiceTypeTaskDTO;
import com.il.sod.rest.dto.db.SpecDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@SuppressWarnings("Duplicates")
@Service
public class ServicesSv extends EntityServicesBase {

  @Autowired
  ServiceTypeRepository serviceTypeRepository;

  @Autowired
  ProductTypeRepository productTypeRepository;

  @Autowired
  ServiceTypeTaskRepository serviceTypeTaskRepository;

  @Autowired
  SpecRepository specRepository;

  @Autowired
  TaskRepository taskRepository;

  @Autowired
  ServiceCategoryRepository serviceCategoryRepository;

  public ServiceTypeDTO saveServiceType(ServiceTypeDTO dto) throws SODAPIException {
    ServiceType entity = ServiceMapper.INSTANCE.map(dto);
    this.saveEntity(serviceTypeRepository, entity);
    return converter.map(entity, ServiceTypeDTO.class);
  }

  public ServiceTypeDTO updateServiceType(ServiceTypeDTO dto) throws SODAPIException {
    ServiceType entity = serviceTypeRepository.findOne(dto.getIdServiceType());
    if (entity == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Service Type not found not found [%s]", dto.getIdServiceType());
    }
    entity = ServiceMapper.INSTANCE.map(dto, entity);
    entity.setServiceCategory(serviceCategoryRepository.findOne(dto.getIdServiceCategory()));
    this.updateEntity(serviceTypeRepository, entity);
    return converter.map(entity, ServiceTypeDTO.class);
  }

  public boolean deleteItem(int id) throws SODAPIException {
    ServiceType entity = getServiceTypeEntity(id);
    this.deleteEntity(serviceTypeRepository, entity.getId());
    return true;
  }

  public List<ServiceTypeDTO> getServiceTypeList(int idServiceType) throws SODAPIException {
    List<ServiceType> entityList;
    if (idServiceType > 0) {
      entityList = serviceTypeRepository.findById(idServiceType);
    } else {
      entityList = this.getEntityList(serviceTypeRepository);
    }
    return entityList.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toList());
  }

  public ServiceTypeDTO getServiceType(int idServiceType) throws SODAPIException {
    ServiceType st = serviceTypeRepository.findOne(idServiceType);
    return ServiceMapper.INSTANCE.map(st);
  }

  public List<ServiceTypeDTO> getPublicServiceType() throws SODAPIException {
    List<ServiceType> listEntities = serviceTypeRepository.findAllPublic();
    return listEntities.stream().map(ServiceMapper.INSTANCE::map).collect(Collectors.toList());
  }

  public ServiceTypeDTO addProducts(int idServiceType, List<ProductTypeDTO> listDto) throws SODAPIException {
    ServiceType serviceType = getServiceTypeEntity(idServiceType);

    Set<Integer> listIds = listDto.stream()
            .map(ProductTypeDTO::getIdProductType)
            .collect(Collectors.toSet());

    List<ProductType> toRemove = serviceType.getProductTypes()
            .stream()
            .filter(s -> !listIds.contains(s.getId()))
            .collect(Collectors.toList());

    toRemove.forEach(serviceType::removeProductType);

    for (ProductTypeDTO dto : listDto) {
      ProductType productType = productTypeRepository.findOne(dto.getIdProductType());
      if (productType == null) {
        throw new SODAPIException(Response.Status.BAD_REQUEST, "ProductType not found %s ", dto.getIdProductType());
      }
      serviceType.addProductType(productType);
    }
    serviceTypeRepository.save(serviceType);
    return ServiceMapper.INSTANCE.map(serviceType);
  }

  public ServiceTypeDTO addSpecs(int idServiceType, List<SpecDTO> listDto) throws SODAPIException {
    ServiceType serviceType = getServiceTypeEntity(idServiceType);
    Set<Integer> listIds = listDto.stream().map(SpecDTO::getIdSpecs).collect(Collectors.toSet());
    List<Spec> toRemove = serviceType.getSpecs()
            .stream()
            .filter(s -> !listIds.contains(s.getIdSpecs()))
            .collect(Collectors.toList());

    toRemove.forEach(serviceType::removeSpec);

    for (SpecDTO specDTO : listDto) {
      Spec spec = specRepository.findOne(specDTO.getIdSpecs());
      if (spec == null) {
        throw new SODAPIException(Response.Status.BAD_REQUEST, "Spec not found %s ", specDTO.getIdSpecs());
      }
      serviceType.addSpec(spec);
    }
    serviceTypeRepository.save(serviceType);
    return ServiceMapper.INSTANCE.map(serviceType);
  }

  public ServiceTypeDTO addServiceTypeTask(int idServiceType, List<ServiceTypeTaskDTO> listDto) throws SODAPIException {
    serviceTypeTaskRepository.removeByServiceType(idServiceType);
    ServiceType serviceType = getServiceTypeEntity(idServiceType);
    for (ServiceTypeTaskDTO serviceTypeTaskDTO : listDto) {
      if (serviceTypeTaskDTO.getTask() == null || serviceTypeTaskDTO.getTask().getIdTask() < 1) {
        throw new SODAPIException(Response.Status.BAD_REQUEST, "one or more Tasks are not valid ");
      }
      Task task = taskRepository.findOne(serviceTypeTaskDTO.getTask().getIdTask());
      if (task == null) {
        throw new SODAPIException(Response.Status.BAD_REQUEST, "task not found %s ", serviceTypeTaskDTO.getTask().getIdTask());
      }
      ServiceTypeTask serviceTypeTask = ServiceMapper.INSTANCE.map(serviceTypeTaskDTO);
      serviceTypeTask.setServiceType(serviceType);
      serviceTypeTask.setTask(task);
      serviceType.addServiceTypeTask(serviceTypeTask);
    }
    serviceType = serviceTypeRepository.save(serviceType);
    return ServiceMapper.INSTANCE.map(serviceType);
  }

  private ServiceType getServiceTypeEntity(int idServiceType) throws SODAPIException {
    ServiceType serviceType = serviceTypeRepository.findOne(idServiceType);
    if (serviceType == null) {
      throw new SODAPIException(Response.Status.BAD_REQUEST, "Item not found");
    }
    return serviceType;
  }

}
