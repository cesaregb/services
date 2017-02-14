package com.il.sod.model.entities;

import com.il.sod.db.dao.IDAO;
import com.il.sod.db.dao.impl.ClientDAO;
import com.il.sod.db.model.entities.Address;
import com.il.sod.db.model.entities.Client;
import com.il.sod.db.model.entities.ClientType;
import com.il.sod.db.model.repositories.ClientRepository;
import com.il.sod.db.model.repositories.ClientSpecification;
import com.il.sod.db.model.repositories.ClientTypeRepository;
import com.il.sod.db.model.repositories.SearchCriteria;
import com.il.sod.config.SpringTestConfiguration;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class ClientDaoTest extends SpringTestConfiguration{
	
	@Autowired
	IDAO<Client, Integer> genericDaoImpl;
	
	@Autowired
	private ClientDAO clientDAO;
	
	@Autowired
	ClientRepository clientRepository;

	@Autowired
	ClientTypeRepository clientTypeRepository;
	
    @Ignore
    @Test
    public void test(){
    	try{
//    		genericDaoImpl.setRepository(clientRepository);
//    		Assert.assertTrue(create());
//    		Assert.assertNotNull(SelfPublishKeyValues.INSTANCE.getProperty("selfpub.registration.api.port"));
//    		Assert.assertTrue(findAll());
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    @Test
    public void testCustomQuery(){
//    	clientDao.findByEmail("cesareg.borjon@gmail.com");
//    	List<Client> c = clientDao.findAll();
//    	List<Client> c = clientReadsDAO.findByEmail("cesareg.borjon@gmail.com");
    	List<Client> c = clientDAO.findByToken("abcd");
//    	List<Client> c = clientDAO.findByToken2("abcd");
    	System.out.println("*************************");
    	for ( Client item : c ){
    		assertNotNull(item.getEmail());
    		System.out.println("email: " + item.getEmail());
    		System.out.println("name: " + item.getName());
    	}
    	System.out.println("*************************");
    }
    
    @Test
    public void testSimpleQuery(){
    	ClientSpecification spec = new ClientSpecification(new SearchCriteria("name", ":", "Name"));
		List<Client> results = clientRepository.findAll(spec);
		for (Client c : results){
			System.out.println("c: " + c.getName() + " " + c.getLastName());
			assertEquals("name not valid", "Name", c.getName());
		}
    }
    
    @Test
    public void testMultipleQuery(){
    	ClientSpecification spec = new ClientSpecification(new SearchCriteria("name", ":", "Name"));
    	ClientSpecification spec2 = new ClientSpecification(new SearchCriteria("email", ":", "email@domain.com.mx"));
    	List<Client> results = clientRepository.findAll(Specifications.where(spec).and(spec2));
    	assertNotNull("No results!!", results);
    	assertTrue("No results!!", (results.size() > 0) );
    	for (Client c : results){
    		System.out.println("c: " + c.getName() + " " + c.getLastName());
    		assertEquals("name not valid", "Name", c.getName());
    	}
    }

    @Test
    public void IMPORT_CLIENTS() throws Exception{
	    String fileName = "Clients.csv";
	    ClassLoader classLoader = ClientDaoTest.class.getClassLoader();
	    File file = new File(classLoader.getResource(fileName).getFile());
	    Scanner in = new Scanner(file);

	    ClientType clientType = clientTypeRepository.findOne(1);

	    String input;
	    while(in.hasNextLine() && !((input = in.nextLine()).equals(""))){
		    String[] fields = input.split(",");
		    if (fields.length > 0){
			    String name = (fields.length > 0)?fields[0]:"";
			    String address = (fields.length > 1)?fields[1]:"";
			    String phone = (fields.length > 2)?fields[2]:"";

			    String[] nameParts = name.split("\\s+");
			    String firstName = nameParts[0];
				String lastName = (nameParts.length > 1)?nameParts[1]:"";

			    Client client = new Client();
			    client.setName(firstName);
			    client.setLastName(lastName);
			    clientType.addClient(client);
			    Address addressObj = new Address();
			    addressObj.setAddress(address);
			    client.addAddress(addressObj);
			    client.setHomePhone(phone);
			    clientRepository.save(client);
		    }
	    }
    }

    @Transactional
    public boolean findAll(){
    	System.out.println("Hola!!!!");
    	List<Client> r = genericDaoImpl.findAll();
    	for (Client c : r){
    		System.out.println("email: " + c.getEmail());
    	}
    	return true;
    }
}