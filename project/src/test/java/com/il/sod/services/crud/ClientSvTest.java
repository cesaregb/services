package com.il.sod.services.crud;

import com.il.sod.config.SpringTestConfiguration;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.mapper.ClientMapper;
import com.il.sod.rest.dto.db.ClientDTO;
import com.il.sod.services.cruds.ClientSv;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;

/**
 * Created by cesaregb on 1/19/17.
 */
public class ClientSvTest extends SpringTestConfiguration {

	@Autowired
	ClientSv clientSv;

	@Autowired
	protected ClientRepository clientRepository;

	String email = RandomStringUtils.randomAlphanumeric(8).toLowerCase() + "-test@domain.com";

	public ClientDTO testClient(){
		return new ClientDTO(0, email,
				"lname", "name", "twt", "rfc",
				"rz", 1, "0123", "0123",
				"0123", null, null, null);
	}

	public void saveClient() throws Exception {
		List<Client> c = clientRepository.findByEmail(email);
		ClientDTO dto;
		if (c.isEmpty()){
			dto = testClient();
			dto = clientSv.saveClient(dto);
		}else{
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
		List<ClientDTO> l = clientSv.getClientsByFilter(map, null, null , null);
		Assert.assertTrue("No Clients found", l.size() > 0);
		System.out.println("*****************");
		l.forEach(c ->{
			System.out.println(c.toString());
		});
		System.out.println("*****************");
		deleteClient();

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


	@Test
	public void addAddress() throws Exception {
		List<Client> c = clientRepository.findAll();
	}

}
