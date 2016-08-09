package com.il.sod.test.endpoints;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.rest.api.web.impl.AppOrdersService;
import com.il.sod.rest.dto.specifics.InpServiceDTO;
import com.il.sod.rest.dto.specifics.InputSpecDTO;
import com.il.sod.rest.dto.specifics.NewOrderDTO;
import com.il.sod.test.config.SpringTestConfiguration;

public class OrderServiceSimTest extends SpringTestConfiguration{
	
	@Autowired
	AppOrdersService appOrdersService;
	
    @Test
    public void testSaveOrder() throws Exception{
    	NewOrderDTO oi = new NewOrderDTO();
    	oi.setIdClient(1);
    	oi.setPrice(35d);
    	oi.setIdAddressPickup(1);
    	oi.setIdAddressDeliver(1);
    	oi.setPickUpDate(new Date());
    	oi.setDeliveryDate(new Date());
    	List<InpServiceDTO> services = new ArrayList<>();
    	InpServiceDTO e = new InpServiceDTO();
    	e.setIdServiceType(1);
    	e.setPrice(35);
    	List<InputSpecDTO> specs = new ArrayList<>();
    	InputSpecDTO s = new InputSpecDTO();
    	s.setIdSpecs(1);
    	s.setValue("2");
    	s.setQuantity(1);
		specs.add(s);
		InputSpecDTO s2 = new InputSpecDTO();
		s2.setIdSpecs(2);
		s2.setQuantity(1);
		s2.setValue("1");
		specs.add(s2 );
		e.setSpecs(specs );
		services.add(e);
		oi.setServices(services);
		appOrdersService.saveOrder(oi);
    }    
}