package com.il.sod.web.endpoints;

import com.il.sod.rest.api.application.AppOrdersService;
import com.il.sod.rest.dto.parse.UIOrderDTO;
import com.il.sod.rest.dto.parse.UIServiceDTO;
import com.il.sod.rest.dto.parse.UISpecDTO;
import com.il.sod.rest.dto.parse.UITransportDTO;
import com.il.sod.config.SpringTestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceSimTest extends SpringTestConfiguration{
	
	@Autowired
	AppOrdersService appOrdersService;
	
    @Test
    public void testSaveOrder() throws Exception{
    	UIOrderDTO oi = new UIOrderDTO();
    	oi.setIdClient(1);
    	oi.setTotal(35d);
	    List<UITransportDTO> lTransport = new ArrayList<>();
	    lTransport.add(new UITransportDTO(1, new Date(), 0, 100));
	    lTransport.add(new UITransportDTO(1, new Date(), 1, 10));
	    oi.setTransport(lTransport);

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