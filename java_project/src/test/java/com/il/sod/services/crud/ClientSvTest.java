package com.il.sod.services.crud;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.services.cruds.ClientSv;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by cesaregb on 1/19/17.
 */
public class ClientSvTest extends SpringTestConfiguration {
  private final static Logger LOGGER = getLogger(ClientSvTest.class);
  
  @Autowired
  ClientSv clientSv;

  @Autowired
  private ClientRepository clientRepository;

  String email;

  @Before
  public void init() {
    email = RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "-test@domain.com";
  }


  public ClientDTO testClient() {
    return new ClientDTO(0, email,
            "lname", "name", "twt", "rfc",
            "rz", 1, "0123", "0123",
            "0123", null, null, null);
  }

  private void saveClient() throws Exception {
    List<Client> c = clientRepository.findByEmail(email);
    ClientDTO dto;
    if (c.isEmpty()) {
      dto = testClient();
      dto = clientSv.saveClient(dto);
    } else {
      dto = clientSv.reactivateClient(email);
    }
  }

  public void deleteClient() throws Exception {
    clientSv.deleteItem(email);
  }

  @Test
  public void createUser() throws Exception {
    String email = RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "-test@domain.com";
    ClientDTO dto = testClient();
    dto.setEmail(email);
    dto = clientSv.saveClient(dto);
    System.out.println("dto: " + dto);
    clientSv.deleteItem(email);
  }

  @Test
  public void findClient() throws Exception {
    saveClient();
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.add("email", "test@domain.com");
    List<ClientDTO> l = clientSv.getClientsByFilter(map, null, null, null);
    Assert.assertTrue("No Clients found", l.size() > 0);
    deleteClient();

  }

  @Test
  public void findClientByName() throws Exception {
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.add("name", "fernando");
    List<ClientDTO> list = clientSv.getClientsByFilter(map, null, null, null);
    Assert.assertTrue("No Clients found", list.size() > 0);
    LOGGER.info("Size: {}", list.size());
  }

  @Test
  public void findClientByPhone() throws Exception {
    MultivaluedMap<String, String> map = new MultivaluedHashMap<>();
    map.add("phone", "1111222333");
    List<ClientDTO> list = clientSv.getClientsByFilter(map, null, "1111222333", null);
    Assert.assertTrue("No Clients found", list.size() > 0);
    LOGGER.info("Size: {}", list.size());
  }

  @Test
  public void updateClient() throws Exception {
    saveClient();
    ClientDTO c = ClientMapper.INSTANCE.map(clientRepository.findByEmail(email).get(0));
    String newName = RandomStringUtils.randomAlphanumeric(8).toLowerCase();
    c.setName(newName);
    clientSv.updateClient(c);
    ClientDTO c2 = clientSv.getClient(String.valueOf(c.getIdClient()));
    Assert.assertTrue("client is not updated", c.equals(c2));
    deleteClient();
  }

}
