package com.il.sod.services.cruds;

import com.il.sod.db.dao.impl.ClientDAO;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.*;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.rest.dto.predicates.DeletablePredicate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by cesaregb on 1/19/17.
 */
@Service
public class ClientSv extends EntityServicesBase {
  private static final String PHONE_TXT = "phone";

  private final static Logger LOGGER = LoggerFactory.getLogger(ClientSv.class);

  @Autowired
  protected ClientRepository clientRepository;

  @Autowired
  private ClientTypeRepository clientTypeRepository;

  @Autowired
  private ClientDAO clientDAO;

  public ClientDTO saveClient(ClientDTO dto) throws SODAPIException {

    if (dto.getIdClientType() == null || dto.getIdClientType() == 0) {
      final String localMessage = "Client Type should not be empty";
      LOGGER.error(localMessage);
      throw new SODAPIException(Response.Status.BAD_REQUEST, localMessage);
    }

    if (clientDAO.findByEmail(dto.getEmail()) != null) {
      final String localMesage = "Email already associated with another User ";
      LOGGER.error(localMesage);
      throw new SODAPIException(Response.Status.BAD_REQUEST, localMesage);
    }

    Client entity = ClientMapper.INSTANCE.map(dto);
    clientTypeRepository.findOne(dto.getIdClientType()).addClient(entity);
    assignDependencyToChilds(entity);
    this.saveEntity(clientRepository, entity);
    return ClientMapper.INSTANCE.map(entity);
  }


  public ClientDTO updateClient(ClientDTO dto) throws SODAPIException {
    // find existing one.
    Client entity = clientRepository.findOne(dto.getIdClient());
    if (entity != null) {
      Client testClient = clientDAO.findByEmail(dto.getEmail());
      if (testClient != null && testClient.getId() != dto.getIdClient()) {
        throw new SODAPIException(Response.Status.NOT_FOUND, "Email is currently used by client " + testClient.getName());
      }
    } else {
      entity = clientDAO.findByEmail(dto.getEmail());
    }

    if (entity == null) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client doesnt exist.");
    }

    if (entity.getDeleted() > 0) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client deleted, please request admin to activate.");
    }
    // in case we changed the client type...  we need to remove it and add it again
    dto.setIdClient(entity.getId());// assign because we are seeking from email not id.

    // remove dependencies to be udated..
    entity.getClientType().removeClient(entity);
    entity = ClientMapper.INSTANCE.map(dto, entity);
    LOGGER.info("***** Client ID " + entity.getId());
    clientTypeRepository.findOne(dto.getIdClientType()).addClient(entity);

    assignDependencyToChilds(entity);
    this.updateEntity(clientRepository, entity);
    return ClientMapper.INSTANCE.map(entity);
  }

  public ClientDTO reactivateClient(String email) throws SODAPIException {
    Client entity = clientDAO.findByEmail(email);
    if (entity == null) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found with email: " + email + " ");
    }
    LOGGER.info("Update entity");
    entity.setDeleted(0);
    this.updateEntity(clientRepository, entity);
    return ClientMapper.INSTANCE.map(entity);
  }


  public boolean deleteItem(int clientId) throws SODAPIException {
    Client entity = clientRepository.findOne(clientId);
    if (entity == null) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found");
    }
    this.softDeleteEntity(clientRepository, entity.getIdClient());

    return true;
  }

  public boolean deleteItem(String email) throws SODAPIException {
    List<Client> clients = clientRepository.findByEmail(email);
    if (clients.isEmpty()) {
      throw new SODAPIException(Response.Status.NOT_FOUND, "Client not found");
    }
    Client entity = clients.get(0);
    this.softDeleteEntity(clientRepository, entity.getIdClient());

    return true;
  }

  public ClientDTO getClient(String clientId) throws SODAPIException {
    return ClientMapper.INSTANCE.map(this.getEntity(clientRepository, Integer.valueOf(clientId)));
  }

  public List<ClientDTO> getClientsByFilter(MultivaluedMap<String, String> queryParams,
                                            String idAddress,
                                            String phone,
                                            String token) throws SODAPIException {

    // logging
    Iterator<String> it;
    if (!queryParams.isEmpty()) {
      it = queryParams.keySet().iterator();
      while (it.hasNext()) {
        String theKey = it.next();
        LOGGER.info(theKey + " : " + queryParams.getFirst(theKey));
      }
    }

    List<Client> entities;
    if (StringUtils.isNotEmpty(phone)) {
      entities = clientDAO.findByPhone(phone);

    } else if (StringUtils.isNotEmpty(idAddress)) {
      entities = clientDAO.findByAddress(Integer.valueOf(idAddress));

    } else if (StringUtils.isNotEmpty(token)) {
      entities = clientDAO.findByToken(token);

    } else if (!queryParams.isEmpty()) {
      List<ClientSpecification> filterList = new ArrayList<>();
      it = queryParams.keySet().iterator();
      while (it.hasNext()) {
        String theKey = it.next();
        if (!theKey.toLowerCase().equals(PHONE_TXT)) {
          ClientSpecification spec = new ClientSpecification(new SearchCriteria(theKey, ":", queryParams.getFirst(theKey)));
          filterList.add(spec);
        }
      }
      SpecificationsBuilder<Client, ClientSpecification> builder = new SpecificationsBuilder<>(filterList);
      entities = clientRepository.findAll(builder.build());
    } else {
      entities = this.getEntityList(clientRepository);
    }

    List<ClientDTO> result = entities.stream().map(ClientMapper.INSTANCE::map)
            .filter(DeletablePredicate.isActive())
            .collect(Collectors.toList());

    return result;
  }

  private void assignDependencyToChilds(Client entity) {
    if (entity.getAddresses() != null) {
      entity.getAddresses().stream().filter(Objects::nonNull).forEach(a -> a.setClient(entity));
    }

    if (entity.getClientPaymentInfos() != null) {
      entity.getClientPaymentInfos().stream()
              .filter(Objects::nonNull)
              .forEach(a -> a.setClient(entity));
    }

    if (entity.getClientBags() != null) {
      entity.getClientBags().stream()
              .filter(Objects::nonNull)
              .forEach(a -> a.setClient(entity));
    }
  }

}
