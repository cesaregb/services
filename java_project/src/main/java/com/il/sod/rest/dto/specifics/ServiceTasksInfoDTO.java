package com.il.sod.rest.dto.specifics;

import com.il.sod.rest.dto.db.ServiceTaskDTO;
import lombok.Data;

import java.util.Set;

@Data
public class ServiceTasksInfoDTO {
	private int idService;
	private String name;
	private String serviceDescription;
	private Set<ServiceTaskDTO> serviceTasks;
	private int idOrder;
}
