package com.il.sod.test.endpoints;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.il.sod.rest.api.web.impl.AppOrdersService;
import com.il.sod.rest.dto.specifics.UIServiceDTO;
import com.il.sod.rest.dto.specifics.UISpecDTO;
import com.il.sod.rest.dto.specifics.UIOrderDTO;
import com.il.sod.test.config.SpringTestConfiguration;

public class OrderServiceSimTest extends SpringTestConfiguration{
	
	@Autowired
	AppOrdersService appOrdersService;
	
    @Test
    public void testSaveOrder() throws Exception{
    	UIOrderDTO oi = new UIOrderDTO();
    	oi.setIdClient(1);
    	oi.setPrice(35d);
    	oi.setIdAddressPickup(1);
    	oi.setIdAddressDeliver(1);
    	oi.setPickUpDate(new Date());
    	oi.setDeliveryDate(new Date());
    	List<UIServiceDTO> services = new ArrayList<>();
    	UIServiceDTO e = new UIServiceDTO();
    	e.setIdServiceType(1);
    	e.setPrice(35);
    	List<UISpecDTO> specs = new ArrayList<>();
    	UISpecDTO s = new UISpecDTO();
    	s.setIdSpecs(1);
    	s.setValue("2");
    	s.setQuantity(1);
		specs.add(s);
		UISpecDTO s2 = new UISpecDTO();
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