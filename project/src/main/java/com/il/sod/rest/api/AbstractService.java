package com.il.sod.rest.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.il.sod.config.jersey.JacksonObjectMapperProvider;
import com.il.sod.db.dao.IDAO;
import com.il.sod.db.model.entities.SoftDeleteEntity;
import com.il.sod.exception.SODAPIException;
import com.il.sod.mapper.BaseMapper;
import com.il.sod.rest.api.helper.ServicesDBHelper;
import com.il.sod.rest.dto.GeneralResponseMessage;
import com.il.sod.rest.util.RestUtil;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;

@Component
@SwaggerDefinition(tags = { @Tag(name = "clients", description = "Client Layer services"),
		@Tag(name = "app-utils", description = "Utilities of the site - store"),
		@Tag(name = "app-orders", description = "Services used by web-app"),
		@Tag(name = "employees", description = "Employee services"),
		@Tag(name = "specs", description = "Specs are the 'type of generics' that uses a service "),
//		@Tag(name = "asset", description = "Assets  - Business assets"),
		@Tag(name = "supplies", description = "Supplies used for specs"),
		@Tag(name = "tasks", description = "Task used in orders or services"),
		@Tag(name = "orders", description = "Order Layer Services"),
		@Tag(name = "services", description = "Service Layer Services"),
		@Tag(name = "payment", description = "Payment Services Client and Orders"),
		@Tag(name = "routes", description = "Route Information,... not user related..."),
		@Tag(name = "products", description = "Products, similar to specs but different!! LOL "),
		@Tag(name = "promotions", description = "Promotions, promotion handling  "),
		@Tag(name = "health", description = "Validate API + MODEL Healt") })
@Path("/v1")
@SuppressWarnings("all")
public abstract class AbstractService{
	
	final static Logger ABS_LOGGER = LoggerFactory.getLogger(AbstractService.class);
	
	protected ObjectMapper mapper;
	protected ServicesDBHelper serviceDbHelper;
	protected MapperFacade converter = BaseMapper.MAPPER_FACTORY.getMapperFacade();
	
	@Autowired
	protected IDAO genericDaoImpl;
	
	protected AbstractService() {
		mapper = JacksonObjectMapperProvider.MAPPER;
		serviceDbHelper = new ServicesDBHelper(this);
	}
	
	public Response castEntityAsResponse(Object entity) throws SODAPIException {
		return castEntityAsResponse(entity, Response.Status.OK);
	}
	
	public Response castEntityAsResponse(Object entity, Status status) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString()).status(status).build();
	}
	
	public String castEntityAsString(Object entity) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, entity);
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return writer.toString();
	}
	
	public Response genericResponse(boolean flag, String message) throws SODAPIException {
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, new GeneralResponseMessage(flag, message));
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
		return Response.ok(writer.toString())
				.status((flag) ? Response.Status.OK : Response.Status.INTERNAL_SERVER_ERROR).build();
	}
	
	public Response genericResponse(boolean flag) throws SODAPIException {
		return genericResponse(flag, "Response");
	}
	
	public Response genericResponse(String message) throws SODAPIException {
		return genericResponse(true, message);
	}
	
	public <DTO> DTO getEntityfromJSON(String json, Class<DTO> entityClass) throws SODAPIException {
		try {
			if (json != null && json.trim().length() > 0) {
				return mapper.readValue(json, entityClass);
			} else {
				throw new SODAPIException(Response.Status.NO_CONTENT,
						"No content to map to Object due to end of input. The JSON object provided is empty, it was expected an object.");
			}
		} catch (Exception e) {
			throw new SODAPIException(e);
		}
	}
	
	public <T> T getJsonISAsObject (InputStream is, Class<T> entityClass) throws SODAPIException {
		try {
			String content = RestUtil.getContentFromInputStream(is);
			T result = mapper.readValue(content, entityClass);
			
			return result;
		} catch (Exception e) {
			throw new SODAPIException(Response.Status.UNAUTHORIZED,"No valid authentication", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getEntity(JpaRepository<T, Integer> repository, Integer id){	
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		return gDao.findById(id);
	}
	
	@Deprecated
	@SuppressWarnings("unchecked")
	protected <T> List<T> getEntityList(JpaRepository<T, Integer> repository){	
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		gDao.setRepository(repository);
		return gDao.findAll();
	}

	@SuppressWarnings("unchecked")
	protected <T> List<T> getEntityList(Class<T> clazz, JpaRepository<T, Integer> repository){
		IDAO<T, Integer> gDao = (IDAO<T, Integer>) this.genericDaoImpl;
		if (SoftDeleteEntity.class.isAssignableFrom(clazz)){
			gDao.setRepository(repository);
			return gDao.findAllActive();
		}else{
			return null;
		}
	}

	public IDAO getGenericDaoImpl() {
		return genericDaoImpl;
	}
}
